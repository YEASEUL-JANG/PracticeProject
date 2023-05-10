package portal.admin.scheduler;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@DisallowConcurrentExecution //하나의 job이 실행될 동안 다른 job이 동시에 실행되지 않게 보장.
public class TestScheduler implements org.quartz.Job {
    private static final Logger logger = LoggerFactory.getLogger(TestScheduler.class);

    @Value("#{service['test.cron']}")
    private String testCronTime;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        checkTest();
    }

    private void checkTest() {
        logger.info("SetupAddTimeManageScheduler Controller execute");
    }
}
