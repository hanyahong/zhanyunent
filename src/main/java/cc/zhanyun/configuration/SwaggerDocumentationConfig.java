package cc.zhanyun.configuration;


import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;

import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.ApiInfo;

import springfox.documentation.service.Contact;

import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.MultipartConfigElement;


@Configuration
public class SwaggerDocumentationConfig {
    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("展云 API")
                .description("项目API").license("").licenseUrl("")
                .termsOfServiceUrl("").version("1.0.1")
                .contact(new Contact("", "", "")).build();
    }


    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("cc.zhanyun.api"))
                .build().apiInfo(apiInfo());
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("20480KB");
        factory.setMaxRequestSize("20480KB");
        return factory.createMultipartConfig();
    }
}

