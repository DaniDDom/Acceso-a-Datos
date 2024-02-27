package com.example.unidad6_1;

import com.example.unidad6_1.model.Employee;
import com.example.unidad6_1.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Unidad61Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Unidad61Application.class, args);
        var repo = context.getBean(EmployeeRepository.class);

        repo.deleteAll();

        List<Employee> employees = List.of(
                new Employee(null, "emp1", "emp1@gmail.com", LocalDate.of(1990,1,1)),
                new Employee(null, "emp5", "emp5@gmail.com", LocalDate.of(1990,1,1)),
                new Employee(null, "emp3", "emp3@gmail.com", LocalDate.of(1990,1,1)),
                new Employee(null, "emp4", "emp4@gmail.com", LocalDate.of(1990,1,1)),
                new Employee(null, "emp5", "emp4@gmail.com", LocalDate.of(1990,1,1))
        );

        repo.saveAll(employees);


        repo.findAll().forEach(System.out::println);

        System.out.println("count: " + repo.countAllByName("emp5"));
        System.out.println("count: " + repo.countAllByNameQuery("emp5"));

        var mongo = context.getBean(MongoTemplate.class);
        Employee employee = new Employee(null, "emp6", "emp6@gmail.com", LocalDate.of(1990,1,1));
        mongo.save(employee);
    }

}
