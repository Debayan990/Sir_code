package org.reni.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("location-service",r->r.path("/api/locations/**")
                        .uri("lb://location-service"))
                .route("auth-service",r->r.path("/api/auth/**")
                .uri("lb://auth-service"))
                .route("department-service",r->r.path("/api/departments/**")
                        .uri("lb://department-service"))
                .route("employee-service",r->r.path("/api/employees/**")
                        .uri("lb://employee-service"))
                .build();





    }
}
