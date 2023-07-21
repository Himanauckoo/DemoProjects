package com.chartercommunication.rewards.configurations;

import com.chartercommunication.rewards.model.Department;
import com.chartercommunication.rewards.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Department department(){
        return new Department();
    }

    @Bean(name = "empWithoutConstructor")
    public Employee employee(){
        return new Employee();
    }
}
