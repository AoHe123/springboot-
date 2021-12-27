package com.example.test.task;


import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @MyTask: ao
 * @time 2021/12/17
 */
@Configuration
public class MyTask {

    @Bean
    public JobDetail MyTaskDetail(){
        return JobBuilder.newJob(MyTaskJob.class).withDescription("commentLikeTaskQuartz").storeDurably().build();
    }
    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(5)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(MyTaskDetail())
                .withIdentity("commentLikeTaskQuartz")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
