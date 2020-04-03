package com.example.cloudfoundrydatabase;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@SpringBootApplication(scanBasePackages = {"com.example.cloudfoundrydatabase"})
@EnableJpaRepositories
public class CloudfoundryDataBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudfoundryDataBaseApplication.class, args);
	}

	@Bean
	public JobDetail jobDetail() {
		return JobBuilder.newJob().ofType(DataBaseJob.class)
				.storeDurably()
				.withIdentity("Qrtz_Job_Detail")
				.withDescription("Invoking database job...")
				.build();
	}

	@Bean
	public Trigger trigger(JobDetail job) {
		return TriggerBuilder.newTrigger()
				.forJob(job)
				.withIdentity("Qrtz_Trigger")
				.withDescription("Database trigger")
				.withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(2))
				.build();
	}

}
