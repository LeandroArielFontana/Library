package libreria2.Springboot.Model.Entities;

import libreria2.Springboot.Model.Entities.Book;
import java.util.List;
import javax.persistence.*;

@Entity
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private Boolean register;
    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(Integer id, String name, Boolean register, List<Book> books) {
        this.id = id;
        this.name = name;
        this.register = register;
        this.books = books;
    }

    public Author() {
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
