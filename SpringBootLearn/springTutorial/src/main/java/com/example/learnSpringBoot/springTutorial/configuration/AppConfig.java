package com.example.learnSpringBoot.springTutorial.configuration;

import com.example.learnSpringBoot.springTutorial.DB;
import com.example.learnSpringBoot.springTutorial.DevDB;
import com.example.learnSpringBoot.springTutorial.ProdDB;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnProperty(name = "project.mode", havingValue = "production")
    public DB getDevDBBean(){
        return new ProdDB();
    }

    @Bean
    @ConditionalOnProperty(name = "project.mode", havingValue = "development")
    public DB getProdDBBean(){
        return new DevDB();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
