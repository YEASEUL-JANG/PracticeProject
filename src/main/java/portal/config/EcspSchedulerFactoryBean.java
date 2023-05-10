package portal.config;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import portal.utils.EncryptDecryptUtil;

public class EcspSchedulerFactoryBean extends SchedulerFactoryBean {
    private static final Logger logger = LoggerFactory.getLogger(EcspSchedulerFactoryBean.class);


    public void setConfigLocation(Resource configLocation) {


        Properties prop = new Properties();
        try {
            prop.load(configLocation.getInputStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        //quartz.properties 파일에 등록된 key
        String parameterPwdKey = "org.quartz.dataSource.quartzDataSource.pwd";
        //spring quartz 설정 Key
        String pwKey = "org.quartz.dataSource.quartzDataSource.password";

        if(prop.containsKey(parameterPwdKey)) {
            String encPassword =  prop.getProperty(parameterPwdKey);
            String plainPswd = EncryptDecryptUtil.decrypt(encPassword);
            prop.setProperty(pwKey, plainPswd);

            prop.remove(parameterPwdKey);
        }
        super.setQuartzProperties(prop);
    }
}
