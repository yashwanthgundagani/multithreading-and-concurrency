package com.example.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessorApplication implements CommandLineRunner {
	@Autowired
	private DataProcessor dataProcessor;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private DataProcessorIVersion dataProcessorIVersion;

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		var customers = customerService.generateCustomer(10_00_000);
		dataProcessorIVersion.process(customers);
		//dataProcessor.process(customers);
	}
}
//Time Taken normally: 5574 ms
//Time Taken with multithreading: 2158 ms, 1668 ms almost 50% reduced time