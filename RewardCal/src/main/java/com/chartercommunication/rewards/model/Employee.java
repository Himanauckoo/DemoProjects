package com.chartercommunication.rewards.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Employee {
    private String empName;
    private int empId;
    private Department department;

    public Employee(){

    }

    @Autowired
    Employee(Department department){
//        this.empName=empName;
//        this.empId=empId;
        this.department=department;
    }
}
