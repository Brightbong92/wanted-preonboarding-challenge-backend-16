package com.wanted.preonboarding.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                    new Info()
                        .title("원티드프리온보딩")
                        .description("티켓 예약 시스템")
                        .version("0.0.1")
                );
    }
}
