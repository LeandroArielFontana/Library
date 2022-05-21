package com.example.Libreria.Entities;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@SQLDelete(sql = "UPDATE Publisher p SET p.register = false WHERE p.id = ?")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private Boolean register;
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;

    public Publisher() {
    }

    public Publisher(String name, List<Book> books) {
        this.name = name;
        this.register = true;
        this.books = books;
    }

    public Publisher(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}

