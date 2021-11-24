package com.example.Libreria3.Service;

import java.util.List;
import java.util.Optional;
import com.example.Libreria3.Entities.Author;
import com.example.Libreria3.Entities.Book;
import com.example.Libreria3.Entities.Publisher;
import com.example.Libreria3.Exceptions.MyException;
import com.example.Libreria3.Repository.IBookRepository;
import com.example.Libreria3.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private IBookRepository iBookRepository;

    @Transactional
    public void delete(Integer id){
        iBookRepository.deleteById(id);
    }

    @Transactional
    public void create(String tittle, Integer year, Long isbn, Integer copies, Author author, Publisher publisher) throws MyException{

        if (!Util.checkISBN(isbn)){
            throw new MyException("El codigo ISBN debe contener numeros de entre 10 - 13 Digitos");
        }

        Book book = new Book();
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setBorrowedCopies(0);
        book.setCopies(copies);
        book.setIsbn(isbn);
        book.setRegister(true);
        book.setRemainingCopies(copies);
        book.setTitle(tittle);
        book.setYear(year);
        iBookRepository.save(book);
    }

    @Transactional
    public void updateBook(String tittle, Integer year, Long isbn, Integer copies, Author author, Publisher publisher, Integer id) throws MyException{

        if (iBookRepository.findById(id).orElse(null)==null){
            throw new MyException("No existe un Libro con ese ID");
        }else if (!Util.checkISBN(isbn)){
            throw new MyException("El codigo ISBN debe contener numeros de entre 10 - 13 Digitos");
        }

        Book book = findById(id);
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
    public Book findById(Integer id) throws MyException{
        Book bookOptional = iBookRepository.findById(id).orElse(null);

        if (bookOptional==null){
            throw new MyException("No existe un Libro con ese ID");
        }

        return bookOptional;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll(){
        return iBookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> availableBooks(){
        return iBookRepository.available();
    }

    @Transactional
    public void registerBook(Integer id){
        iBookRepository.register(id);
    }
}

