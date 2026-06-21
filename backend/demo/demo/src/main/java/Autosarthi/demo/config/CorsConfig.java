package Autosarthi.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://127.0.0.1:5500",
                                "http://localhost:5500",
                                "https://autosarathi.vercel.app",
                                "https://autosarathi-6v796f7uv-shubhambhatupatil-5720s-projects.vercel.app"
                        )
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
