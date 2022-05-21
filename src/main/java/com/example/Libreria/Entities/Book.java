package com.example.Libreria.Entities;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@SQLDelete(sql = "UPDATE Book b SET b.register = false WHERE b.id = ?")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Long isbn;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private Integer copies;
    private Integer borrowedCopies;
    private Integer remainingCopies;
    private Boolean register;
    @ManyToOne
    private Author author;
    @ManyToOne
    private Publisher publisher;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;

    public Book(Long isbn, String title, Integer year, Integer copies, Author author, Publisher publisher) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.copies = copies;
        this.borrowedCopies = 0;
        this.remainingCopies = copies;
        this.register = true;
        this.author = author;
        this.publisher = publisher;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getBorrowedCopies() {
        return borrowedCopies;
    }

    public void setBorrowedCopies(Integer borrowedCopies) {
        this.borrowedCopies = borrowedCopies;
    }

    public Integer getRemainingCopies() {
        return remainingCopies;
    }

    public void setRemainingCopies(Integer remainingCopies) {
        this.remainingCopies = remainingCopies;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

}