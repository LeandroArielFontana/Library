package com.example.Libreria3.Service;

import java.util.List;
import java.util.Optional;

import com.example.Libreria3.Entities.Publisher;
import com.example.Libreria3.Exceptions.MyException;
import com.example.Libreria3.Repository.IPublisherRepository;
import com.example.Libreria3.Util.Util;
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

        Publisher publisher = new Publisher();
        publisher.setName(name);
        iPublisherRepository.save(publisher);

    }

    @Transactional
    public void delete(Integer id){
        iPublisherRepository.deleteById(id);
    }

    @Transactional
    public void register (Integer id){
        iPublisherRepository.register(id);
    }

    @Transactional
    public void updateName(Integer id, String name) throws MyException{

        if (iPublisherRepository.findById(id).orElse(null)==null){
            throw new MyException("No existe una Editorial con ese ID");
        }else if (!Util.checkNames(name)) {
            throw new MyException("El nombre contiene numeros");
        }

        iPublisherRepository.updateName(id, name);
    }

    @Transactional
    public List<Publisher> findAll(){
        return iPublisherRepository.findAll();
    }

    @Transactional
    public Publisher findById (Integer id) throws MyException {
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
