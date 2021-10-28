package libreria2.Springboot.Service;

import java.util.List;
import java.util.Optional;
import libreria2.Springboot.Model.Entities.Author;
import libreria2.Springboot.Repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {
    
    @Autowired
    private IAuthorRepository iAuthorRepository;
    
    @Transactional
    public void create(String name){
        Author author = new Author();
        author.setName(name);
        iAuthorRepository.save(author);
    }
    
    @Transactional
    public void update(Integer id, String name){
        iAuthorRepository.updateName(id, name);
    }
    
    @Transactional
    public void delete(Integer id){
        iAuthorRepository.deleteById(id);
    }
    
    @Transactional
    public Author findById(Integer id){
        Optional<Author> authorOptional = iAuthorRepository.findById(id);
        return authorOptional.orElse(null);
    }
    
    @Transactional(readOnly = true)
    public List<Author> findAll(){
        return iAuthorRepository.findAll();
    }
}