package com.example.Libreria3.Service;

import java.util.List;
import java.util.Optional;
import com.example.Libreria3.Entities.Author;
import com.example.Libreria3.Exceptions.MyException;
import com.example.Libreria3.Repository.IAuthorRepository;
import com.example.Libreria3.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    @Autowired
    private IAuthorRepository iAuthorRepository;

    @Transactional
    public void create(String name) throws MyException{

        Author author = new Author();
        author.setName(name);
        author.setRegister(true);
        iAuthorRepository.save(author);
    }

    @Transactional
    public void update(Integer id, String name) throws MyException{

        if (iAuthorRepository.findById(id).orElse(null)==null) {
            throw new MyException("No existe un Autor con ese ID");
        }

        Author author = iAuthorRepository.findById(id).orElse(null);
        author.setName(name);
        iAuthorRepository.save(author);
    }

    @Transactional
    public void delete(Integer id){
        iAuthorRepository.deleteById(id);
    }

    @Transactional
    public void register(Integer id) {
        iAuthorRepository.register(id);
    }

    @Transactional
    public Author findById(Integer id) throws MyException{
        Author authorOptional = iAuthorRepository.findById(id).orElse(null);

        if (authorOptional==null) {
            throw new MyException("No existe un Autor con ese ID");
        }
        return authorOptional;
    }

    @Transactional(readOnly = true)
    public List<Author> findAll(){
        return iAuthorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Author> availableAuthors(){
        return iAuthorRepository.available();
    }
}