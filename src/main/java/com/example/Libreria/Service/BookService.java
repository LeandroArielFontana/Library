package com.example.Libreria.Service;

import com.example.Libreria.Entities.Author;
import com.example.Libreria.Entities.Book;
import com.example.Libreria.Entities.Publisher;
import com.example.Libreria.Exceptions.MyException;
import com.example.Libreria.Repository.IBookRepository;
import com.example.Libreria.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private IBookRepository iBookRepository;

    @Transactional
    public void delete(Integer id) {
        iBookRepository.deleteById(id);
    }

    @Transactional
    public void create(String title, int year, long isbn, int copies, Author author, Publisher publisher) throws MyException {

        if (!Util.checkISBN(isbn)) {
            throw new MyException("El codigo ISBN debe contener numeros de entre 10 - 13 Digitos");
        }

        iBookRepository.save(new Book(isbn, title, year, copies, author, publisher));
    }

    @Transactional
    public void updateBook(String tittle, int year, Long isbn, int copies, Author author, Publisher publisher, int id) throws MyException {
        Book book = iBookRepository.findById(id).orElse(null);

        if (book == null) {
            throw new MyException("No existe un Libro con ese ID");
        } else if (!Util.checkISBN(isbn)) {
            throw new MyException("El codigo ISBN debe contener numeros de entre 10 - 13 Digitos");
        }

        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCopies(copies);
        book.setIsbn(isbn);
        book.setRegister(true);
        book.setTitle(tittle);
        book.setYear(year);
        iBookRepository.save(book);
    }

    @Transactional
    public Book findById(int id) throws MyException {
        Book book = iBookRepository.findById(id).orElse(null);

        if (book == null) throw new MyException("No existe un Libro con ese ID");

        return book;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return iBookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> availableBooks() {
        return iBookRepository.available();
    }

    @Transactional
    public void registerBook(Integer id) {
        iBookRepository.register(id);
    }
}

