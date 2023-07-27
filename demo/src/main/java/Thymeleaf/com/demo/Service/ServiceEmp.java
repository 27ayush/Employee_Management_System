package Thymeleaf.com.demo.Service;

import java.util.*;

import Thymeleaf.com.demo.JPAEntity.Employee;

public interface ServiceEmp {

    List<Employee>  getAllEmployee();

    Employee addEmployee(Employee employeeDto);

    void deleteById(Long id);


    Employee getEmployeeById(Long id);

    Employee updateEmployee(Employee employee);



}
