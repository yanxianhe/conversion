package com.yxh.conversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.oas.annotations.EnableOpenApi;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@EnableOpenApi
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = { 
        "com.yxh.conversion.config", 
        "com.yxh.conversion.*.service",
        "com.yxh.conversion.*.controller" })
public class ConversionApplication {
        private static final Logger log = LogManager.getLogger(ConversionApplication.class);
        public static void main(String[] args) {
            SpringApplication.run(ConversionApplication.class, args);
            log.info("**************** start ****************");    
        }
}
