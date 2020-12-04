package hr.tvz.segota.studapp.student;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {
    @Bean
    public JobDetail studentsJobDetail() {
        return JobBuilder.newJob(StudentJob.class).withIdentity("studentJob").storeDurably().build();
    }

    @Bean
    public Trigger studentsJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10).repeatForever();

        // DODATNI ZADATAK
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 12 ? * MON,FRI");
        // POZIV DODATNOG ZADATKA
        return TriggerBuilder.newTrigger().forJob(studentsJobDetail())
                .withIdentity("studentsTrigger").withSchedule(cronScheduleBuilder).build();
    }
}
