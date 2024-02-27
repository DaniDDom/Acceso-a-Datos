package com.example.unidad6_1.repository;

import com.example.unidad6_1.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {
    Long countAllByName(String name);

    @Query(value = "{name:  ?0}", count = true)
    Long countAllByNameQuery(String name);
}
