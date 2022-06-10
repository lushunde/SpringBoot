package com.lushunde.springboot.quartz;

import com.lushunde.springboot.config.constant.ExceptionEnum;
import com.lushunde.springboot.config.exception.BusinException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.text.ParseException;

/**
 * 定时调度管理类
 * 原则上trigger与Job一一对应
 *
 * @author cuixd
 *
 */
@Component
@Slf4j
public class QuartzManager {


	private Scheduler scheduler ;

	public QuartzManager(Scheduler scheduler){
		this.scheduler = scheduler;
	}

	@PostConstruct
	public void init() throws BusinException {
		try {
			scheduler.start();
			log.info("QuartzManager启动成功");
		} catch (SchedulerException e) {
			throw BusinException.newInstance(ExceptionEnum.QUARTZ_INIT_ERROR,e).log(log);
		}
	}

	@PreDestroy
	public void destroy() throws BusinException {
		try {
			scheduler.shutdown();
			log.info("QuartzManager关闭完成");
		} catch (SchedulerException e) {
			throw BusinException.newInstance(ExceptionEnum.QUARTZ_DESTROY_ERROR,e).log(log);
		}
	}

	public void clearAll() throws SchedulerException, ParseException, BusinException {

		scheduler.clear();

	}



	/**
	 * @Description 动态增加定时调度
	 * @Author Bellus
	 * @Date 2022/6/9 19:50
	 * @Version 1.0.0
	 * @Param [jobName, jobGroupName, clazz, cronExpression]
	 * @return void
	 **/
	public void addJob(String name, String groupName,Class<? extends Job> clazz, String cronExpression) throws SchedulerException, ParseException, BusinException {

			this.addJob(name, groupName, clazz, cronExpression, null, 5);

	}



	/**
	 * @Description 动态增加定时调度
	 * @Author Bellus
	 * @Date 2022/6/9 19:50
	 * @Version 1.0.0
	 * @Param [jobName, jobGroupName, clazz, cronExpression, param]
	 * @return void
	 **/
	public void addJob(String name, String groupName, Class<? extends Job> clazz, String cronExpression, Object param) throws SchedulerException, ParseException, BusinException {
		this.addJob( name,  groupName,   clazz, cronExpression, param,5);
	}



	/**
	 * @Description 动态增加定时调度
	 * @Author Bellus
	 * @Date 2022/6/9 21:28
	 * @Version 1.0.0
	 * @Param [name, groupName, clazz, cronExpression, param, priority]
	 * @return void
	 **/
	public void addJob(String name, String groupName, Class<? extends Job> clazz, String cronExpression, Object param,Integer priority) throws SchedulerException, ParseException, BusinException {

		if(scheduler.checkExists(JobKey.jobKey(name, groupName)) ) {

			log.error("添加的任务Trigger已经存在，name={},groupName={}",name,groupName);
			removeJob(name,groupName);
		}

		// 作业的实力 （包含job 和  jobkey）
		JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(name,groupName).build();
		if(!ObjectUtils.isEmpty(param)){
			// 设置Job执行时传入参数
			jobDetail.getJobDataMap().put("param", param);
		}

		// 构建 corn
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		// 定义corn 触发器
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name,groupName).withSchedule(cronScheduleBuilder).withPriority(priority).build();


		// 调度器 设置 job
		scheduler.scheduleJob(jobDetail, trigger);

		// 启动
		if (!scheduler.isShutdown()) {
			scheduler.start();
		}

		log.info("Quertz添加job成功 name={},groupName={}",name,groupName);



	}



	/**
	 * 修改一个任务的触发时间
	 *
	 * @param triggerName       触发器名
	 * @param triggerGroupName  触发器组名
	 * @param cron              时间设置，参考quartz说明文档
	 */
	public void modifyJobTime(String triggerName, String triggerGroupName, String cron) throws BusinException {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}

			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cron)) {
				// 触发器
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
				// 触发器名,触发器组
				triggerBuilder.withIdentity(triggerName, triggerGroupName);
				triggerBuilder.startNow();
				// 触发器时间设定
				triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
				// 创建Trigger对象
				trigger = (CronTrigger) triggerBuilder.build();
				// 方式一 ：修改一个任务的触发时间
				scheduler.rescheduleJob(triggerKey, trigger);

			}
		} catch (Exception e) {
			throw BusinException.newInstance(ExceptionEnum.QUARTZ_DESTROY_ERROR,e).log(log);
		}
	}









	/**
	 * @Description 删除job
	 * @Author Bellus
	 * @Date 2022/6/9 21:28
	 * @Version 1.0.0
	 * @Param [name, groupName]
	 * @return void
	 **/
	public void removeJob(String name, String groupName) throws SchedulerException, ParseException {
		JobKey jobkey = JobKey.jobKey(name, groupName);
		TriggerKey triggerkey = TriggerKey.triggerKey(name, groupName);
		// 停止触发器
		scheduler.pauseTrigger(triggerkey);
		// 移除触发器
		scheduler.unscheduleJob(triggerkey);
		// 删除任务
		scheduler.deleteJob(jobkey);
		log.info("已经删除任务 name={},groupName={}",name,groupName);
	}




	/**
	 * @Description 恢复任务
	 * @Author Bellus
	 * @Date 2022/6/9 21:29
	 * @Version 1.0.0
	 * @Param [jobName, jobGroup]
	 * @return void
	 **/
	public void remuseJob(String jobName, String jobGroup) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		if (jobDetail == null) {
			return;
		}
		scheduler.resumeJob(jobKey);
	}




}
