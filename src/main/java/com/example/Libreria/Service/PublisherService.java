package com.example.Libreria.Service;

import java.util.List;

import com.example.Libreria.Entities.Publisher;
import com.example.Libreria.Exceptions.MyException;
import com.example.Libreria.Repository.IPublisherRepository;
import com.example.Libreria.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublisherService {

    @Autowired
    private IPublisherRepository iPublisherRepository;

    @Transactional
    public void create(String name) throws MyException{

        if (!Util.checkNames(name)){
            throw new MyException("El nombre contiene numeros o espacios");
        }

        iPublisherRepository.save(new Publisher(name));
    }

    @Transactional
    public void delete(int id){
        iPublisherRepository.deleteById(id);
    }

    @Transactional
    public void register (int id){
        iPublisherRepository.register(id);
    }

    @Transactional
    public void updateName(int id, String name) throws MyException{
        Publisher publisher = iPublisherRepository.findById(id).orElse(null);

        if (publisher == null){
            throw new MyException("No existe una Editorial con ese ID");
        }else if (!Util.checkNames(name)) {
            throw new MyException("El nombre contiene numeros");
        }

        publisher.setName(name);
        iPublisherRepository.save(publisher);
    }

    @Transactional
    public List<Publisher> findAll(){
        return iPublisherRepository.findAll();
    }

    @Transactional
    public Publisher findById (int id) throws MyException {
        Publisher publisherOptional = iPublisherRepository.findById(id).orElse(null);

        if (publisherOptional==null){
            throw new MyException("No existe una Editorial con ese ID");
        }

        return publisherOptional;
    }

    @Transactional(readOnly = true)
    public List<Publisher> availablePublishers(){
        return iPublisherRepository.available();
    }


}
