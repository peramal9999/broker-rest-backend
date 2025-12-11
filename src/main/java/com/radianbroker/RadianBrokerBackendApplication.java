package com.radianbroker;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAsync
@EnableScheduling
 public class RadianBrokerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RadianBrokerBackendApplication.class, args);
	}

}
