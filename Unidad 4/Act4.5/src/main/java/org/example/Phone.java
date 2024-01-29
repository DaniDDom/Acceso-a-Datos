package org.example;

import jakarta.persistence.*;

@Entity
@Table(name="Telefono")
public class Phone {



    @Column(name="Telefono")
    private String telefono;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="id_telefono")
    private int idTelefono;

    @ManyToOne
    @JoinColumn(name="person_id", foreignKey = @ForeignKey(name="PERSON_ID_FK"))
    private Person person;

    public Phone(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public Phone() {

    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
