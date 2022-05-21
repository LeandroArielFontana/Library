package com.example.Libreria.Service;

import java.util.List;
import java.util.Optional;
import com.example.Libreria.Entities.Author;
import com.example.Libreria.Exceptions.MyException;
import com.example.Libreria.Repository.IAuthorRepository;
import com.example.Libreria.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    @Autowired
    private IAuthorRepository iAuthorRepository;

    @Transactional
    public void create(String name) throws MyException{

        if(Util.checkWhiteSpace(name)){
            throw new MyException("El campo no puede estar vacio");
        } else if (!Util.checkNames(name)){
            throw new MyException("El nombre contiene numeros o espacios");
        }

        Author author = new Author();
        author.setName(name);
        author.setRegister(true);
        iAuthorRepository.save(author);
    }

    @Transactional
    public void update(Integer id, String name) throws MyException{

        if (iAuthorRepository.findById(id).orElse(null)==null) {
            throw new MyException("No existe un Autor con ese ID");
        }else if(Util.checkWhiteSpace(name)){
            throw new MyException("El campo no puede estar vacio");
        } else if (!Util.checkNames(name)){
            throw new MyException("El nombre contiene numeros o espacios");
        }
        iAuthorRepository.updateName(id, name);
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