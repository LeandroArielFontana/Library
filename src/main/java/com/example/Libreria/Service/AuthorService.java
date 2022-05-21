package com.example.Libreria.Service;

import com.example.Libreria.Entities.Author;
import com.example.Libreria.Exceptions.MyException;
import com.example.Libreria.Repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private IAuthorRepository iAuthorRepository;

    @Transactional
    public void create(String name) throws MyException {
        iAuthorRepository.save(new Author(name));
    }

    @Transactional
    public void update(int id, String name) throws MyException {
        Author author = iAuthorRepository.findById(id).orElse(null);

        if (author == null) {
            throw new MyException("No existe un Autor con ese ID");
        }

        author.setName(name);
        iAuthorRepository.save(author);
    }

    @Transactional
    public void delete(int id) {
        iAuthorRepository.deleteById(id);
    }

    @Transactional
    public void register(int id) {
        iAuthorRepository.register(id);
    }

    @Transactional
    public Author findById(int id) throws MyException {
        Author author = iAuthorRepository.findById(id).orElse(null);

        if (author == null) {
            throw new MyException("No existe un Autor con ese ID");
        }

        return author;
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return iAuthorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Author> availableAuthors() {
        return iAuthorRepository.available();
    }
}