package com.example.Libreria3.Controller;

import com.example.Libreria3.Entities.Author;
import com.example.Libreria3.Entities.Book;
import com.example.Libreria3.Entities.Publisher;
import com.example.Libreria3.Exceptions.MyException;
import com.example.Libreria3.Service.AuthorService;
import com.example.Libreria3.Service.BookService;
import com.example.Libreria3.Service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private BookService bookService;

    @GetMapping
    public ModelAndView showAll(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("book");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if(flashMap != null){
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("success", flashMap.get("success"));
        }

        mav.addObject("books", bookService.findAll());
        mav.addObject("title", "Libros");
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createBook(){
        ModelAndView mav = new ModelAndView("book-form");
        mav.addObject("book", new Book());
        mav.addObject("authors", authorService.availableAuthors());
        mav.addObject("publishers", publisherService.availablePublishers());
        mav.addObject("title", "Crear Libro");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editBook(@PathVariable Integer id, RedirectAttributes attributes) {
        ModelAndView mav = new ModelAndView("book-form");

        try {
            mav.addObject("book", bookService.findById(id));
            mav.addObject("authors", authorService.findAll());
            mav.addObject("publishers", publisherService.findAll());
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/book");
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/book");
        }

        mav.addObject("title", "Editar Libro");
        mav.addObject("action", "edit");
        return mav;
    }

    @PostMapping("/save")
    public RedirectView saveBook(@RequestParam String title, @RequestParam Integer year, @RequestParam Long isbn, @RequestParam Integer copies, @RequestParam Author author, @RequestParam Publisher publisher, RedirectAttributes attributes){

        try{
            bookService.create(title, year, isbn, copies, author, publisher);
            attributes.addFlashAttribute("error", "El libro ha sido creado exitosamente!");
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView ("/book");
    }

    @PostMapping("/edit")
    public RedirectView updateBook(@RequestParam String title, @RequestParam Integer year, @RequestParam Long isbn, @RequestParam Integer copies, @RequestParam Author author, @RequestParam Publisher publisher, @RequestParam Integer id, RedirectAttributes attributes){

        try{
            bookService.updateBook(title, year, isbn, copies, author, publisher, id);
            attributes.addFlashAttribute("success", "El libro ha sido actualizado exitosamente!");
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView ("/book");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteBook(@PathVariable Integer id){
        bookService.delete(id);
        return new RedirectView ("/book");
    }

    @PostMapping("/register/{id}")
    public RedirectView registerBook(@PathVariable Integer id){
        bookService.registerBook(id);
        return new RedirectView("/book");
    }
}