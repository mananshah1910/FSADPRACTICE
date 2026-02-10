package com.klu.model;

// Import all JPA annotations
import javax.persistence.*;

// Marks this class as a JPA Entity (table)
@Entity

// Maps this class to "emp" table in database
@Table(name = "emp")
public class Employee {

    // Marks empid as Primary Key
    @Id

    // Auto-generates empid using database auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empid;

    // Column for employee name
    private String empname;

    // Column for employee salary
    private double salary;

    // Many employees belong to one department
    @ManyToOne

    // Foreign key column in emp table
    @JoinColumn(name = "deptid")
    private Department department;

    // Setter method to set employee ID
    public void setEmpId(int empid) {
        this.empid = empid;
    }

    // Getter method to return employee ID
    public int getEmpId() {
        return empid;
    }

    // Setter method to set employee name
    public void setEmpName(String empname) {
        this.empname = empname;
    }

    // Getter method to return employee name
    public String getEmpName() {
        return empname;
    }

    // Setter method to set salary
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Getter method to return salary
    public double getSalary() {
        return salary;
    }

    // Setter method to set department
    public void setDepartment(Department department) {
        this.department = department;
    }

    // Getter method to return department
    public Department getDepartment() {
        return department;
    }
}