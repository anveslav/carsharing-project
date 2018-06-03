package com.rusoft.carsharing.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.rusoft.carsharing.service")
public class AppConfig {
}
