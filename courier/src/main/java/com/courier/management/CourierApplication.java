package com.courier.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.courier.management.parcel.adapter"})
@SpringBootApplication
public class CourierApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourierApplication.class, args);
    }

}
