package com.example.Libreria.Service;

import com.example.Libreria.Entities.Book;
import com.example.Libreria.Entities.Borrowed;
import com.example.Libreria.Entities.Client;
import com.example.Libreria.Exceptions.MyException;
import com.example.Libreria.Repository.IBookRepository;
import com.example.Libreria.Repository.IBorrowedRepository;
import com.example.Libreria.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BorrowedService {

    @Autowired
    private IBorrowedRepository iBorrowedRepository;
    @Autowired
    private IBookRepository iBookRepository;

    @Transactional
    public void create(Book book, Client client, Date returnDate, Date borrowingDate) throws MyException {

        if (book.getRemainingCopies() <= 0) {
            throw new MyException("El libro no puede ser prestado");
        } else if (Util.checkDates(borrowingDate, returnDate)) {
            throw new MyException("La fecha del prestamo tiene que ser menor a la del retorno");
        }

        book.setRemainingCopies(book.getRemainingCopies() - 1);
        book.setBorrowedCopies(book.getBorrowedCopies() + 1);
        iBorrowedRepository.save(new Borrowed(borrowingDate, returnDate, book, client));
    }

    @Transactional
    public void updateBorrowed(int id, Book book, Client client, Date returnDate, Date borrowingDate) throws MyException {
        Borrowed borrowed = iBorrowedRepository.findById(id).orElse(null);

        if (borrowed == null) {
            throw new MyException("No existe un Prestamo con ese ID");
        } else if (book.getRemainingCopies() <= 0) {
            throw new MyException("El libro no puede ser prestado");
        } else if (Util.checkDates(borrowingDate, returnDate)) {
            throw new MyException("La fecha del prestamo tiene que ser menor a la del retorno");
        }

        borrowed.setBook(book);
        borrowed.setClient(client);
        borrowed.setReturnDate(returnDate);
        borrowed.setBorrowingDate(borrowingDate);
        borrowed.setRegister(true);

        iBorrowedRepository.save(borrowed);
    }

    @Transactional
    public Borrowed findById(int id) throws MyException {
        Borrowed borrowed = iBorrowedRepository.findById(id).orElse(null);

        if (borrowed == null) {
            throw new MyException("No existe un Prestamo con ese ID");
        }

        return borrowed;
    }

    @Transactional(readOnly = true)
    public List<Borrowed> findAll() {
        return iBorrowedRepository.findAll();
    }

    @Transactional
    public void deleteLoan(int id) {
        Borrowed borrowed = iBorrowedRepository.findById(id).orElse(null);
        Book book = iBookRepository.findById(borrowed.getBook().getId()).orElse(null);
        book.setRemainingCopies(book.getRemainingCopies() + 1);
        book.setBorrowedCopies(book.getBorrowedCopies() - 1);
        borrowed.setBook(book);
        iBorrowedRepository.deleteById(id);
    }

    @Transactional
    public void register(int id) {
        Borrowed borrowed = iBorrowedRepository.findById(id).orElse(null);
        Book book = iBookRepository.findById(borrowed.getBook().getId()).orElse(null);
        book.setRemainingCopies(book.getRemainingCopies() - 1);
        book.setBorrowedCopies(book.getBorrowedCopies() + 1);
        borrowed.setBook(book);
        iBorrowedRepository.register(id);
    }
}
