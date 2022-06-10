package com.lushunde.springboot.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * @Description 这个类用于启动SpringBoot时，加载作业。run方法会自动执行。   另外可以使用 ApplicationRunner
 * @Author Bellus
 * @Date 2022/6/9 21:21
 * @Version 1.0.0
 * @Param
 * @return
 **/
@Slf4j
@Component
public class InitStartSchedule implements CommandLineRunner {

	@Autowired
	private QuartzManager quartzManager;


	@Override
	public void run(String... args) throws Exception {
		/**
		 * 用于程序启动时加载定时任务，并执行已启动的定时任务(只会执行一次，在程序启动完执行)
		 */

		// 清除
		quartzManager.clearAll();


		quartzManager.addJob("JVM2", "JVMGROUP2", JvmInfoJob.class, "0/10 * * * * ?", new String[]{"10", "25", "zhangsqan"});
		log.info("JVM完成....................................");



	}
	

}
