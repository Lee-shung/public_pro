package com.lys.publicpro.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李无敌
 * @date 2021年01月18日 17:08
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi(){
        //header添加token
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build()
                .groupName("小顺子api文档")
                .globalOperationParameters(pars);
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("李无敌接口文档")
                .description("api公共文档")
//                .termsOfServiceUrl("http://1.117.65.157/doc.html")
                .termsOfServiceUrl("http://www.liwudi.cn/doc.html")
                .version("1.0")
                .build();
    }
}
