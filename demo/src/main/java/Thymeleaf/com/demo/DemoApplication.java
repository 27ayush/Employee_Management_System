package Thymeleaf.com.demo;

import Thymeleaf.com.demo.JPAEntity.Employee;
import Thymeleaf.com.demo.Respository.RepositoryJPA;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}

}
