package com.lushunde.springboot;

import com.lushunde.springboot.config.log.mdc.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.lushunde.springboot.mapper")
@Slf4j
public class Application {



	public static void main(String[] args) {

		MDC.put("traceId", TraceIdUtil.getTraceId());

		SpringApplication.run(Application.class, args);

		log.info("启动完成....................................");
	}


}
