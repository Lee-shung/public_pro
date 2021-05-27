package com.lys.publicpro;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.lys.publicpro.common.db.mapper")
@EnableSwagger2
@EnableAsync
@EnableSwaggerBootstrapUI
public class PublicProApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicProApplication.class, args);
    }

}
