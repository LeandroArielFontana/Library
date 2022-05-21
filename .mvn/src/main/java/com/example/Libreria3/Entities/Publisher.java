package com.example.Libreria.Entities;

import org.hibernate.annotations.SQLDelete;

import java.util.List;
import javax.persistence.*;

@Entity
@SQLDelete(sql = "UPDATE Publisher p SET p.register = false WHERE p.id = ?")
public class Publisher{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private Boolean register;
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    public Publisher() {
    }

    public Publisher(Integer id, String name, Boolean register, List<Book> books) {
        this.id = id;
        this.name = name;
        this.register = register;
        this.books = books;
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

