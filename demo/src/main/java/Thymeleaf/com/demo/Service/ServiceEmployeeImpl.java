package Thymeleaf.com.demo.Service;


import Thymeleaf.com.demo.JPAEntity.Employee;
import Thymeleaf.com.demo.Respository.RepositoryJPA;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceEmployeeImpl implements ServiceEmp{

    private ModelMapper modelMapper;
    private RepositoryJPA repositoryJPA;

    @Override
    public List<Employee> getAllEmployee() {

       return repositoryJPA.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {

        return repositoryJPA.save(employee);

    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repositoryJPA.findById(id).get();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return repositoryJPA.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        repositoryJPA.deleteById(id);

    }


}
