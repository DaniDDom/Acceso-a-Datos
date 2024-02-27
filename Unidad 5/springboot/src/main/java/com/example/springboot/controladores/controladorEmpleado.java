package com.example.springboot.controladores;


import com.example.springboot.DTO.EmpleadosDTO;
import com.example.springboot.dao.IDepartamentosDAO;
import com.example.springboot.dao.IEmpleadosDAO;
import com.example.springboot.modelo.DepartamentosEntity;
import com.example.springboot.modelo.EmpleadosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/empleados")
public class controladorEmpleado {

    @Autowired
    IEmpleadosDAO empleadosDAO;

    @Autowired
    IDepartamentosDAO departamentosDAO;

    @GetMapping
    public List<EmpleadosEntity> buscarEmpleados() {
        return (List<EmpleadosEntity>) empleadosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadosEntity> buscarEmpleadosPorId(@PathVariable(value="id") int id) {
        Optional<EmpleadosEntity> empleado =empleadosDAO.findById(id);

        if(empleado.isPresent()) {
            return ResponseEntity.ok().body(empleado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EmpleadosEntity guardarEmpleado(@Validated @RequestBody EmpleadosEntity empleado) {
        return empleadosDAO.save(empleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable(value="id") int id) {
        Optional<EmpleadosEntity> empleado = empleadosDAO.findById(id);

        if(empleado.isPresent()) {
            empleadosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody EmpleadosEntity nuevoEmpleado, @PathVariable(value="id") int id) {
        Optional<EmpleadosEntity> empleado = empleadosDAO.findById(id);
        if(empleado.isPresent()) {
            empleado.get().setNombre(nuevoEmpleado.getNombre());
            empleado.get().setPuesto(nuevoEmpleado.getPuesto());
            empleado.get().setDepartamentosByDepno(nuevoEmpleado.getDepartamentosByDepno());empleado.get().setDepno(nuevoEmpleado.getDepno());
            empleadosDAO.save(empleado.get());
            return ResponseEntity.ok().body("Update");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/dto/{id}")
    public ResponseEntity<EmpleadosDTO> buscarEmpleadoDTOPorId(@PathVariable (value = "id") int id) {
        Optional<EmpleadosEntity> employee = empleadosDAO.findById(id);
        if (employee.isPresent()) {
            Optional<DepartamentosEntity> departamento = departamentosDAO.findById(employee.get().getDepno());

            EmpleadosDTO empleadosDTO = new EmpleadosDTO();
            empleadosDTO.setEmpno((employee.get().getEmpno()));
            empleadosDTO.setNombre(employee.get().getNombre());
            empleadosDTO.setPuesto(employee.get().getPuesto());
            empleadosDTO.setDepno(departamento.get().getDepno());
            empleadosDTO.setDepartamentoNombre(departamento.get().getNombre());
            empleadosDTO.setDepartamentoUbicacion(departamento.get().getUbicacion());
            return ResponseEntity.ok().body(empleadosDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
