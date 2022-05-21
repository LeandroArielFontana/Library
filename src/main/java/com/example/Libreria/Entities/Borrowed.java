package com.example.Libreria.Entities;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
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
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;

    public Borrowed(Date borrowingDate, Date returnDate, Book book, Client client) {
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.register = true;
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
