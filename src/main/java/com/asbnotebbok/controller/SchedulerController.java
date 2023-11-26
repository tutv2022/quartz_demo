package com.asbnotebbok.controller;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Calendar;

import static com.asbnotebbok.controller.trigger.CustomScheduleBuilder.customSchedule;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Slf4j
@RestController
public class SchedulerController {

    @Autowired
    private Scheduler scheduler;

    @GetMapping("/schedule-job")
    public String scheduleJob(@RequestParam("msg") String message) throws SchedulerException {

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("message", message);

        JobKey jobKey = new JobKey("MessagePrintingJob");

        String uuid = UUID.randomUUID().toString();

        JobDetail jobDetail = JobBuilder.newJob()
                .withIdentity(uuid)
                .setJobData(jobDataMap)
                .withDescription("Simple message printing Job.")
                .ofType(MyMessagePrinter.class)
                .storeDurably()
                .build();

//        SimpleTriggerl
//
//                ;


//        Trigger trigger1 = TriggerBuilder.newTrigger()
//                .withIdentity(uuid)
//                .forJob(jobDetail)
//                .withSchedule(
//                        simpleSchedule().repeatForever().withIntervalInSeconds(5)
//                )
//                .startAt(Date.from(Instant.now().plus(10, ChronoUnit.SECONDS)))
//                .withDescription("Simple message printing Job trigger.")
//                .build();

        // Custom Triggers
//        Trigger trigger2 = TriggerBuilder.newTrigger()
//                .withIdentity(uuid)
//                .forJob(jobDetail)
//                .withSchedule(
//                        customSchedule().repeatForever().withIntervalInSeconds(5)
//                )
//                .startAt(Date.from(Instant.now().plus(10, ChronoUnit.SECONDS)))
//                .withDescription("Simple message printing Job trigger.")
//                .build();
//
//        List<Integer> triggerDates = Arrays.asList(1,2,3,4,5);
//
//        trigger2.getJobDataMap().put("repeat", 10);
//        trigger2.getJobDataMap().put("calender", 1);
//        trigger2.getJobDataMap().put("elements", triggerDates);

        //trigger1.getJobDataMap()

//        DailyTimeIntervalTrigger trigger = newTrigger()
//                .withSchedule(
//                        dailyTimeIntervalSchedule()
//                                .startingDailyAt(new TimeOfDay(8, 0))
//                                .withIntervalInMinutes(15)
//                                .endingDailyAfterCount(3)
//                        // inTimeZone not present on DailyTimeIntervalScheduleBuilder
//                )
//                .build();


        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, 2); //minus number would decrement the days


        Trigger trigger2 = TriggerBuilder.newTrigger()
                //.withIdentity(jobInputDTO.getCode(), jobGroup.getName() )
                .forJob(jobDetail)
                .withSchedule(cronSchedule("0 55 20 1,2,3,4,5,6,7,11,12,13,14,15,16,17,25,26 11/2 ? *"))
                //.endAt( cal.getTime())
                .build();

        log.info("Scheduling printing message Job with message :{}", message);
        scheduler.scheduleJob(jobDetail, trigger2);
        return "Job scheduled!!";
    }
}
