package Thymeleaf.com.demo.Controller;


import Thymeleaf.com.demo.JPAEntity.Employee;
import Thymeleaf.com.demo.Model.Helper;
import Thymeleaf.com.demo.Respository.RepositoryJPA;
import Thymeleaf.com.demo.Service.ServiceEmp;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

@Controller
@AllArgsConstructor
public class EmployeeController {

    private RepositoryJPA repositoryJPA;
    private ServiceEmp  serviceEmp;



    @GetMapping("/details")
    public String listEmployee(Model model){
        model.addAttribute("employees", serviceEmp.getAllEmployee());
        return "employees";
    }


    @GetMapping("/employee/new")
    public String createStudentForm(Model model) {

        // create student object to hold student form data
        Employee emp = new Employee();
        model.addAttribute("employee", emp);
        return "create_student";

    }


    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") Employee emp){

        serviceEmp.addEmployee(emp);

        return "redirect:/details";
    }

    @GetMapping("/employee/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model){
        model.addAttribute("employee", serviceEmp.getEmployeeById(id));
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id,
                                @ModelAttribute("employee") Employee emp,
                                Model model) {

        // get student from database by id
        Employee existingEmployee = serviceEmp.getEmployeeById(id);
        existingEmployee.setId(id);
        existingEmployee.setName(emp.getName());
        existingEmployee.setEmail(emp.getEmail());
        existingEmployee.setDepartment(emp.getDepartment());

        // save updated student object
        serviceEmp.updateEmployee(existingEmployee);
        return "redirect:/details";
    }


    @GetMapping("employees/{id}")
    public String deleteEmployee(@PathVariable Long id){
         serviceEmp.deleteById(id);
         return "redirect:/details";
    }

    @GetMapping("/downloadExcel")
    public ResponseEntity<ByteArrayResource> downloadExcel() {
        List<Employee> employees = serviceEmp.getAllEmployee();
        ByteArrayInputStream in = Helper.dataToExcel(employees);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=employees.xlsx");

        // Convert ByteArrayInputStream to a byte array
        byte[] excelBytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            excelBytes = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(excelBytes));
    }
}
