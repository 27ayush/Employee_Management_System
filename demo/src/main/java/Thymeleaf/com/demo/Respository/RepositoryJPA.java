package Thymeleaf.com.demo.Respository;

import Thymeleaf.com.demo.JPAEntity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryJPA extends JpaRepository<Employee, Long> {

}
