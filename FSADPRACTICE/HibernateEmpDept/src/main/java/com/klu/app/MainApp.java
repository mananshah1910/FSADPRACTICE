package com.klu.app;

import java.util.*;

// Hibernate core classes
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

// Entity classes
import com.klu.model.Department;
import com.klu.model.Employee;

// Hibernate utility class
import com.klu.util.HibernateUtil;

public class MainApp {

    // SessionFactory is created once and reused
    static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        // Menu runs until user chooses Exit
        do {
            System.out.println("\n===== Employee Menu =====");
            System.out.println("1. Insert Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter Your Choice : ");

            choice = sc.nextInt();

            // Switch case for menu options
            switch (choice) {
                case 1:
                    insertEmployee(sc);
                    break;
                case 2:
                    viewEmployee(sc);
                    break;
                case 3:
                    updateEmployee(sc);
                    break;
                case 4:
                    deleteEmployee(sc);
                    break;
                case 5:
                    System.out.println("Thank you");
                    break;
                default:
                    System.out.println("Invalid choice !");
            }

        } while (choice != 5);

        // Close resources
        factory.close();
        sc.close();
    }

    // ----------------------------- INSERT ---------------------------------
    // Inserts a new Department and Employee
    private static void insertEmployee(Scanner sc) {

        // Open Hibernate session
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Taking department input
        System.out.print("Enter Department Name : ");
        String deptName = sc.next();

        // Creating Department object
        Department dept = new Department();
        dept.setDeptName(deptName);

        // Saving department
        session.save(dept);

        // Taking employee details
        System.out.print("Enter Employee Name : ");
        String empName = sc.next();

        System.out.print("Enter Salary : ");
        double salary = sc.nextDouble();

        // Creating Employee object
        Employee emp = new Employee();
        emp.setEmpName(empName);
        emp.setSalary(salary);

        // Linking employee with department
        emp.setDepartment(dept);

        // Saving employee
        session.save(emp);

        // Commit transaction
        tx.commit();
        session.close();

        System.out.println("Employee data inserted Successfully");
    }

    // ----------------------------- VIEW ---------------------------------
    // Fetches employee details by ID
    private static void viewEmployee(Scanner sc) {

        Session session = factory.openSession();

        System.out.print("Enter Employee Id : ");
        int id = sc.nextInt();

        // Fetch employee from database
        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            System.out.println("Employee Name : " + emp.getEmpName());
            System.out.println("Salary : " + emp.getSalary());
            System.out.println("Department : " + emp.getDepartment().getDeptName());
        } else {
            System.out.println("Employee not found");
        }

        session.close();
    }

    // ----------------------------- UPDATE ---------------------------------
    // Updates employee name and salary
    private static void updateEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee Id to Update : ");
        int id = sc.nextInt();

        // Fetch employee
        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            System.out.print("Enter New Name : ");
            emp.setEmpName(sc.next());

            System.out.print("Enter New Salary : ");
            emp.setSalary(sc.nextDouble());

            // Update employee
            session.update(emp);
            tx.commit();

            System.out.println("Employee updated successfully");
        } else {
            System.out.println("Employee not found");
            tx.rollback();
        }

        session.close();
    }

    // ----------------------------- DELETE ---------------------------------
    // Deletes employee by ID
    private static void deleteEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee Id to Delete : ");
        int id = sc.nextInt();

        // Fetch employee
        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            session.delete(emp);
            tx.commit();
            System.out.println("Employee deleted successfully");
        } else {
            System.out.println("Employee not found");
        }

        session.close();
    }
}
