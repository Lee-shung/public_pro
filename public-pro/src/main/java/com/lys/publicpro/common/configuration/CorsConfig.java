package com.lys.publicpro.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 李无敌
 * @date 2021年02月04日 16:19
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")

                .allowedOriginPatterns("*")

                .allowCredentials(true)

                .allowedMethods("GET", "POST", "DELETE", "PUT")

                .maxAge(3600);

    }
}
