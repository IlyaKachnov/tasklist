package com.demo.tasklist;

import com.demo.tasklist.configuration.DataSourceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(DataSourceProperties.class)
public class TasklistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasklistApplication.class, args);
	}

}
