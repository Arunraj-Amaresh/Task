package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeModel {

    @JsonProperty("id")
    private String id;

    @JsonProperty("employeeNme")
    private String employee_name;

    @JsonProperty("employeeSalary")
    private String employee_salary;

    @JsonProperty("age")
    private String age;

    
    public EmployeModel(String id, String employeeName, String employeeSalary, String age) {
        this.id = id;
        this.employee_name = employeeName;
        this.employee_salary = employeeSalary;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employee_name;
    }

    public void setEmployeeName(String employeeName) {
        this.employee_name = employeeName;
    }

    public String getEmployeeSalary() {
        return employee_salary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.employee_salary = employeeSalary;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "EmployeModel{" +
                "id='" + id + '\'' +
                ", employeeName='" + employee_name + '\'' +
                ", employeeSalary='" + employee_salary + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
