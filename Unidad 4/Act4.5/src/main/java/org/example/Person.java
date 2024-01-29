package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Persona")
public class Person {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_Persona")
    @Id
    private int person_id;

    @Column(name="Nombre")
    private String nombre;

    @ElementCollection
    @CollectionTable(name="Telefonos", joinColumns=@JoinColumn(name="person_id"))
    @Column(name="Telefonos")
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Phone> lista_telefonos = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }
    public Person() {

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Person(String nombre) {
        this.nombre = nombre;
    }
    public void addPhone(Phone phone) {
        lista_telefonos.add(phone);
        phone.setPerson(this);
    }
    public void removePhone(Phone phone) {
        lista_telefonos.remove(phone);
        phone.setPerson(null);
    }
}
