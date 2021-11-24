package com.example.Libreria3.Service;

import com.example.Libreria3.Entities.Book;
import com.example.Libreria3.Entities.Borrowed;
import com.example.Libreria3.Entities.Client;
import com.example.Libreria3.Exceptions.MyException;
import com.example.Libreria3.Repository.IBorrowedRepository;
import com.example.Libreria3.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowedService {

    @Autowired
    private IBorrowedRepository iBorrowedRepository;

    @Transactional
    public void create(Book book, Client client, Date returnDate, Date borrowingDate) throws MyException {

        if (book.getRemainingCopies()<=0){
            throw new MyException("El libro no puede ser prestado");
        }else if (Util.checkDates(borrowingDate,returnDate)){
            throw new MyException("La fecha del prestamo tiene que ser menor a la del retorno");
        }

        Borrowed borrowed = new Borrowed();
        book.setRemainingCopies(book.getRemainingCopies()-1);
        book.setBorrowedCopies(book.getBorrowedCopies()+1);
        borrowed.setBook(book);
        borrowed.setClient(client);
        borrowed.setReturnDate(returnDate);
        borrowed.setBorrowingDate(borrowingDate);
        borrowed.setRegister(true);
        iBorrowedRepository.save(borrowed);
    }

    @Transactional
    public void updateBorrowed (Integer id, Book book, Client client, Date returnDate, Date borrowingDate) throws MyException {
        if (iBorrowedRepository.findById(id).orElse(null)==null){
            throw new MyException("No existe un Prestamo con ese ID");
        }else if (book.getRemainingCopies()<=0){
            throw new MyException("El libro no puede ser prestado");
        }else if (Util.checkDates(borrowingDate,returnDate)){
            throw new MyException("La fecha del prestamo tiene que ser menor a la del retorno");
        }
        Borrowed borrowed = findById(id);
        borrowed.setBook(book);
        borrowed.setClient(client);
        borrowed.setReturnDate(returnDate);
        borrowed.setBorrowingDate(borrowingDate);
        borrowed.setRegister(true);
        iBorrowedRepository.save(borrowed);
    }

    @Transactional
    public Borrowed findById (Integer id) throws MyException {
        Borrowed borrowedOptional = iBorrowedRepository.findById(id).orElse(null);

        if (borrowedOptional==null){
            throw new MyException("No existe un Prestamo con ese ID");
        }

        return borrowedOptional;
    }

    @Transactional(readOnly = true)
    public List<Borrowed> findAll (){
        List<Borrowed> borroweds = iBorrowedRepository.findAll();
        return borroweds;
    }

    @Transactional
    public void deleteLoan (Integer id){
        iBorrowedRepository.deleteById(id);
    }

    @Transactional
    public void register (Integer id){
        iBorrowedRepository.register(id);
    }
}
