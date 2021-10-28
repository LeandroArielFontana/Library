package libreria2.Springboot.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import libreria2.Springboot.Model.Entities.Publisher;
import libreria2.Springboot.Repository.IPublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    @Autowired
    private IPublisherRepository iPublisherRepository;

    @Transactional
    public void create(String name) {

        Publisher publisher = new Publisher();
        publisher.setName(name);

        iPublisherRepository.save(publisher);
    }

    @Transactional
    public void delete(Integer id){
        iPublisherRepository.deleteById(id);
    }
    
    @Transactional
    public void updateName(Integer id, String name){
        iPublisherRepository.updateName(id, name);
    }

    @Transactional
    public List<Publisher> findAll(){
       return iPublisherRepository.findAll();
    }
    
    @Transactional
    public Publisher findById (Integer id){
        Optional<Publisher> publisherOptional = iPublisherRepository.findById(id);
        return publisherOptional.orElse(null);
    }
}
