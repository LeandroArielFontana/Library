package com.example.Libreria.Entities;

import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@SQLDelete(sql = "UPDATE Client c SET c.register = false WHERE c.id = ?")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Long dni;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    private String phoneNumber;
    private Boolean register;

    public Client() {
    }

    public Client(Integer id, Long dni, String name, String lastName, String phoneNumber, Boolean register) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.register = register;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }
}
