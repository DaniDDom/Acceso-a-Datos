package com.example.springboot.dao;

import com.example.springboot.modelo.DepartamentosEntity;
import com.example.springboot.modelo.EmpleadosEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IDepartamentosDAO extends CrudRepository<DepartamentosEntity, Integer> {

DepartamentosEntity findByNombre(String nombre);
DepartamentosEntity findByUbicacion(String ubicacion);

DepartamentosEntity findByNombreContainingIgnoreCase(String nombre);

}
