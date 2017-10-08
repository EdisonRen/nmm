package com.edisonren.nmm.config;

import com.edisonren.nmm.Config.DaoConfig;
import com.edisonren.nmm.service.NmmService;
import com.edisonren.nmm.service.NmmServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by edison on 9/3/17.
 */
@Configuration
@Import({DaoConfig.class, SenderConfig.class})
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(value = {"com.edisonren.nmm"})
public class NmmServiceConfig {

    @Bean
    public NmmService nmmService() {
        return new NmmServiceImpl();
    }
}
