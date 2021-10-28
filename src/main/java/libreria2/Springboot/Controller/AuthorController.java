package libreria2.Springboot.Controller;

import libreria2.Springboot.Model.Entities.Author;
import libreria2.Springboot.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/author")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;
    
    @GetMapping
    public ModelAndView showAll(){
        ModelAndView mav = new ModelAndView("author");
        mav.addObject("authors", authorService.findAll());
        return mav;
    }
    
    @GetMapping("/create")
    public ModelAndView createAuthor (){
        ModelAndView mav = new ModelAndView("author-form");
        mav.addObject("author", new Author());
        mav.addObject("tittle", "Crear Autor");
        mav.addObject("action", "save");
        return mav;
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView editAuthor (@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("author-form");
        mav.addObject("author", authorService.findById(id));
        mav.addObject("tittle", "Editar Autor");
        mav.addObject("action", "edit");
        return mav;
    }
    
    @PostMapping("/save")
    public RedirectView saveAuthor(@RequestParam String name){
        authorService.create(name);
        return new RedirectView("/author");
    }
    
    @PostMapping("/edit")
    public RedirectView updateAuthor(@RequestParam Integer id, @RequestParam String name){
        authorService.update(id, name);
        return new RedirectView("/author");
    }
    
    @PostMapping("/delete/{id}")
    public RedirectView deleteAuthor(@PathVariable Integer id){
        authorService.delete(id);
        return new RedirectView("/author");
    }
}
