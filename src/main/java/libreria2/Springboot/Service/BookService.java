package libreria2.Springboot.Service;

import java.util.List;
import java.util.Optional;
import libreria2.Springboot.Model.Entities.Author;
import libreria2.Springboot.Model.Entities.Book;
import libreria2.Springboot.Model.Entities.Publisher;
import libreria2.Springboot.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private IBookRepository iBookRepository;
    @Autowired
    private IAuthorRepository iAuthorRepository;
    @Autowired
    private IPublisherRepository iPublisherRepository;
    
    @Transactional
    public void delete(Integer id){
        iBookRepository.deleteById(id);
    }
    
//    @Transactional
//    public void updateTittle (Long isbn, String tittle){
//        iBookRepository.updateTittle(isbn, tittle);
//    }
      @Transactional
    public void create(String tittle, Integer remainingCopies, Integer year, Long isbn, Integer copies, Integer borrowedCopies, Author author, Publisher publisher) {
        Book book = new Book();
        book.setAuthor(iAuthorRepository.findById(author.getId()).get());
        book.setPublisher(iPublisherRepository.findById(publisher.getId()).get());
        book.setBorrowedCopies(borrowedCopies);
        book.setCopies(copies);
        book.setIsbn(isbn);
        book.setRegister(true);
        book.setRemainingCopies(remainingCopies);
        book.setTittle(tittle);
        book.setYear(year);
        iBookRepository.save(book);
    }

    @Transactional
    public void updateBook(String tittle, Integer remainingCopies, Integer year, Long isbn, Integer copies, Integer borrowedCopies, Author author, Publisher publisher, Integer id){
        Book book = findById(id);
        book.setAuthor(iAuthorRepository.findById(author.getId()).get());
        book.setPublisher(iPublisherRepository.findById(publisher.getId()).get());
        book.setBorrowedCopies(borrowedCopies);
        book.setCopies(copies);
        book.setIsbn(isbn);
        book.setRegister(true);
        book.setRemainingCopies(remainingCopies);
        book.setTittle(tittle);
        book.setYear(year);
        iBookRepository.save(book);
    }
    
//    @Transactional
//    public void updateCopies(Long isbn, Integer copies) {
//        iBookRepository.updateCopies(isbn, copies);
//    }
    
    @Transactional
    public Book findById(Integer id){
        Optional<Book> bookOptional = iBookRepository.findById(id);
        return bookOptional.orElse(null);
    }
    
    @Transactional(readOnly = true)
    public List<Book> findAll(){
        return iBookRepository.findAll();
    }
}
