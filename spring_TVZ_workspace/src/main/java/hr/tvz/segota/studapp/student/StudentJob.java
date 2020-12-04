package hr.tvz.segota.studapp.student;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.stream.Collectors;

public class StudentJob extends QuartzJobBean {

    private StudentService studentService;

    private String name;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("---------------- Quartz Scheduler task ----------------");
        System.out.println("Task: Prošlo vrijeme okidanja -" + jobExecutionContext.getPreviousFireTime());
        System.out.println("Task: Zakazano vrijeme okidanja  -" + jobExecutionContext.getScheduledFireTime());
        System.out.println("Task: Sadašnje vrijeme okidanja  -" + jobExecutionContext.getFireTime());
        System.out.println("Task: Slijedeće vrijeme okidanja  -" + jobExecutionContext.getNextFireTime());


        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        SchedulerConfig inputData = (SchedulerConfig) jobDetail.getJobDataMap().get("studentJob");

        List<StudentDTO> students = studentService.findAll().stream().collect(Collectors.toList());

        System.out.println("Ovo su trenutno upisani studenti:");
        System.out.println("------------------------------");
        for (StudentDTO s : students) {
            System.out.println(s.getJmbag() + "-" + s.getFirstName() + " " + s.getLastName());
        }
        System.out.println("------------------------------");
    }
}
