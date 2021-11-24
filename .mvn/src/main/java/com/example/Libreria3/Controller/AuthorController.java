package com.example.Libreria3.Controller;

import com.example.Libreria3.Entities.Author;
import com.example.Libreria3.Exceptions.MyException;
import com.example.Libreria3.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ModelAndView showAll(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("author");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if(flashMap != null){
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("success", flashMap.get("success"));
        }

        mav.addObject("title", "Autores");
        mav.addObject("authors", authorService.findAll());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createAuthor (){
        ModelAndView mav = new ModelAndView("author-form");
        mav.addObject("author", new Author());
        mav.addObject("title", "Crear Autor");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editAuthor (@PathVariable Integer id, RedirectAttributes attributes){
        ModelAndView mav = new ModelAndView("author-form");

        try {
            mav.addObject("author", authorService.findById(id));
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/author");
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/author");
        }

        mav.addObject("title", "Editar Autor");
        mav.addObject("action", "edit");
        return mav;
    }

    @PostMapping("/save")
    public RedirectView saveAuthor(@RequestParam String name, RedirectAttributes attributes) {
        try{
            authorService.create(name);
            attributes.addFlashAttribute("success", "El autor ha sido creado exitosamente!");
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView("/author");
    }

    @PostMapping("/edit")
    public RedirectView updateAuthor(@RequestParam Integer id, @RequestParam String name, RedirectAttributes attributes){
        try{
            authorService.update(id, name);
            attributes.addFlashAttribute("success", "El autor ha sido editado exitosamente!");
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
        }

        return new RedirectView("/author");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteAuthor(@PathVariable Integer id){
        authorService.delete(id);
        return new RedirectView("/author");
    }

    @PostMapping("/register/{id}")
    public RedirectView registerAuthor(@PathVariable Integer id){
        authorService.register(id);
        return new RedirectView("/author");
    }
}