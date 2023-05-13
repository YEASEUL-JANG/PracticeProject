package com.quartz.scheduling.config.quartz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class QuartzConfig {

    private final DataSource dataSource;
    private final ApplicationContext applicationContext;
    private final PlatformTransactionManager platformTransactionManager;
    private final QuartzJobsListener quartzJobsListener;
    private final QuartzTriggersListener quartzTriggersListener;

    @Autowired
    private Environment env;


    @PostConstruct
    public void init() {
        log.debug("QuartzConfig initialized.");
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();

        quartzScheduler.setDataSource(dataSource);
        quartzScheduler.setTransactionManager(platformTransactionManager);
        quartzScheduler.setOverwriteExistingJobs(true);
        quartzScheduler.setSchedulerName("test-quartz-scheduler");
        quartzScheduler.setGlobalJobListeners(quartzJobsListener);
        quartzScheduler.setGlobalTriggerListeners(quartzTriggersListener);

        // custom job factory of spring with DI support for @Autowired!
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        quartzScheduler.setJobFactory(jobFactory);
        quartzScheduler.setQuartzProperties(quartzProperties());

        return quartzScheduler;
    }

    @Bean
    public Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();

        Properties properties = null;
        try {
            propertiesFactoryBean.afterPropertiesSet();
            properties = propertiesFactoryBean.getObject();

        } catch (IOException e) {
            log.warn("Cannot load quartz.properties.");
        }

        return properties;
    }

}
