package com.klu.model;

// Import JPA annotations
import javax.persistence.*;
import java.util.List;

// Marks this class as a Hibernate/JPA Entity (table)
@Entity

// Maps this class to "dept" table in database
@Table(name = "dept")
public class Department {

    // Marks this field as Primary Key
    @Id

    // Auto-generates ID using database auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deptid;   // primary key

    // Column for department name
    private String deptname;

    // One Department has many Employees
    // mappedBy = "department" means Employee class has a field named "department"
    // cascade = CascadeType.ALL means all operations propagate to Employee
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

    // Setter method to set department ID
    public void setDeptId(int deptid) {
        this.deptid = deptid;
    }

    // Getter method to return department ID
    public int getDeptId() {
        return deptid;
    }

    // Setter method to set department name
    public void setDeptName(String deptname) {
        this.deptname = deptname;
    }

    // Getter method to return department name
    public String getDeptName() {
        return deptname;
    }
}