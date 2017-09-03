package com.edisonren.nmm.config;

import com.edisonren.nmm.service.NmmService;
import com.edisonren.nmm.service.NmmServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by edison on 9/3/17.
 */
@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(value = {"com.edisonren.nmm"})
public class NmmServiceConfig {

    @Bean
    public NmmService nmmService() {
        return new NmmServiceImpl();
    }
}
