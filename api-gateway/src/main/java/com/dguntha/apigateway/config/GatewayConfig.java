package com.dguntha.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r
                        .path("/api/product/**")
                        .uri("lb://product-service")) // Assuming "product-service" is the Eureka service ID of your product service
                .route("order-service", r -> r
                        .path("/api/order/**")
                        .uri("lb://order-service"))
                .route("inventory-service", r -> r
                        .path("/api/order/**")
                        .uri("lb://inventory-service"))
                //access to discover server
                .route("discovery-server", r -> r
                        .path("/**")
                        .uri("http://localhost:8761")

                )
                .build();
    }
}
