package com.example.Libreria.Entities;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
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
    @OneToOne
    private UserSecurity userSecurity;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;

    public Client() {
    }

    public Client(Long dni, String name, String lastName, String phoneNumber, UserSecurity userSecurity) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.register = true;
        this.userSecurity = userSecurity;
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

    public UserSecurity getUserSecurity() {
        return userSecurity;
    }

    public void setUserSecurity(UserSecurity userSecurity) {
        this.userSecurity = userSecurity;
    }
}
