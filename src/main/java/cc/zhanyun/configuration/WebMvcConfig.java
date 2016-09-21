package cc.zhanyun.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cc.zhanyun.util.AccessTokenVerifyInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public AccessTokenVerifyInterceptor tokenVerifyInterceptor() {

        return new AccessTokenVerifyInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns(
                new String[]{"/client/**"});

        registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns(
                new String[]{"/projectoffer/**"});

        registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns(
                new String[]{"/project/**"});

        registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns(
                new String[]{"/resources/**"});

        registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns(
                new String[]{"/location/**"});

        registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns(
                new String[]{"/file/**"});
        registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns(
                new String[]{"/user/**"});

        super.addInterceptors(registry);
    }

}

