package com.example.quartz.service;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerService {

    public void startScheduler() throws SchedulerException {
        // Create JobDetail
        JobDetail job = JobBuilder.newJob(
                QuartzServiceImpl.class)
                .withIdentity("myJob", "group1")
                .build();

        // Create Trigger (Runs every 5 seconds)
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5) // Run every 5 seconds
                        .repeatForever())
                .build();

        // Trigger trigger = TriggerBuilder.newTrigger()
        // .withIdentity("cronTrigger", "group1")
        // .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")) // Runs
        // every 10 seconds
        // .build();

        // Create Scheduler
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start(); // Start the scheduler
        scheduler.scheduleJob(job, trigger);
    }

}
