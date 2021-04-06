package com.neliolucas.zeroip.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Nelio
 * @date 24/03/2021
 */

@Configuration
public class Config {

    @Bean
    public RestTemplate template()
    {
        return new RestTemplate();
    }


}
