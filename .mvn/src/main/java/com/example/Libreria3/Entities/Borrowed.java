package com.example.Libreria.Entities;

import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;

@Entity
@SQLDelete(sql = "UPDATE Borrowed b SET b.register = false WHERE b.id = ?")
public class Borrowed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date borrowingDate;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    private Boolean register;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Client client;

    public Borrowed(Integer id, Date borrowingDate, Date returnDate, Boolean register, Book book, Client client) {
        this.id = id;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.register = register;
        this.book = book;
        this.client = client;
    }

    public Borrowed() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
