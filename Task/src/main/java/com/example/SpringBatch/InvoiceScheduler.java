package com.example.SpringBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class InvoiceScheduler {

	@Scheduled(cron = "0 0/5 * 1/1 * ?")
	public void invoiceScheduler() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("batch-config.xml");

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		Job job = (Job) context.getBean("csvJob");

		JobExecution execution = jobLauncher.run(job, new JobParameters());
		System.out.println("Exit Status : " + execution.getStatus());

	}

}