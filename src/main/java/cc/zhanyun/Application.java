package cc.zhanyun;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MultipartConfigFactory;

import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.filter.CorsFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"cc.zhanyun"})
public class Application {
    // 限制上传文件按大小


    // 主函数
    public static void main(String[] args) throws Exception {
        new SpringApplication(new Object[]{Application.class}).run(args);
    }

    // 跨域访问设置
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(Boolean.valueOf(true));
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",
                corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
/*    */ public MultipartConfigElement multipartConfigElement()
/*    */ {
/* 24 */
        MultipartConfigFactory factory = new MultipartConfigFactory();
/* 25 */
        factory.setMaxFileSize("20480KB");
/* 26 */
        factory.setMaxRequestSize("20480KB");
/* 27 */
        return factory.createMultipartConfig();
/*    */
    }
}
