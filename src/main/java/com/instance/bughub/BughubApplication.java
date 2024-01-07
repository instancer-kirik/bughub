package com.instance.bughub;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BughubApplication {

	//private static final Logger logger = LogManager.getLogger(BughubApplication.class);
	public static void main(String[] args) {

		//logger.trace("Entering application.");



		SpringApplication.run(BughubApplication.class, args);
	}
	//log4j has three components
	//1 - Logger        2 - Appender          3 - Layout
	//which classes      where to store      how to print
	//Priority messages for multiple stages/apps

	//TRACE: To print messages for multiple stages/apps
	//DEBUG: To print the final result of process
	//INFO: Current step
	//WARN: App related messages
	//ERROR: To provide details about exception
	//FATAL: Environment related issues
	// study lease - microservices

}
