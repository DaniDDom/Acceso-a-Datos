package com.example.springboot.dao;

import com.example.springboot.modelo.EmpleadosEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IEmpleadosDAO extends CrudRepository<EmpleadosEntity, Integer> {
    EmpleadosEntity findByPuesto(String puesto);
    EmpleadosEntity findByDepnoGreaterThanEqual(int depno);

    @Query("select e from EmpleadosEntity e where e.nombre like %:patron%")
    EmpleadosEntity findByName(@Param("patron") String patron);
}

