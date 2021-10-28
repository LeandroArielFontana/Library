package libreria2.Springboot.Controller;

import libreria2.Springboot.Model.Entities.*;
import libreria2.Springboot.Service.AuthorService;
import libreria2.Springboot.Service.BookService;
import libreria2.Springboot.Service.PublisherService;
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
@RequestMapping("/book")
public class BookController {
    
    @Autowired
    private AuthorService authorService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public ModelAndView showAll(){
        ModelAndView mav = new ModelAndView("book");
        mav.addObject("books", bookService.findAll());
        return mav;
    }
    
    @GetMapping("/create")
    public ModelAndView createBook(){
        ModelAndView mav = new ModelAndView("book-form");
        mav.addObject("book", new Book());
        mav.addObject("authors", authorService.findAll());
        mav.addObject("publishers", publisherService.findAll());
        mav.addObject("tittle", "Crear Libro");
        mav.addObject("action", "save");
        return mav;
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView editBook(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("book-form");
        mav.addObject("book", bookService.findById(id));
        mav.addObject("authors", authorService.findAll());
        mav.addObject("publishers", publisherService.findAll());
        mav.addObject("tittle", "Editar Libro");
        mav.addObject("action", "edit");
        return mav;
    }
    
    @PostMapping("/save")
    public RedirectView saveBook(@RequestParam String tittle, @RequestParam Integer remainingCopies, @RequestParam Integer year, @RequestParam Long isbn, @RequestParam Integer copies, @RequestParam Integer borrowedCopies, @RequestParam Author author, @RequestParam Publisher publisher){
        bookService.create(tittle, remainingCopies, year, isbn, copies, borrowedCopies, author, publisher);
        return new RedirectView ("/book");
    }
    
    @PostMapping("/edit")
    public RedirectView updateBook(@RequestParam String tittle, @RequestParam Integer remainingCopies, @RequestParam Integer year, @RequestParam Long isbn, @RequestParam Integer copies, @RequestParam Integer borrowedCopies, @RequestParam Author author, @RequestParam Publisher publisher, @RequestParam Integer id){
        bookService.updateBook(tittle, remainingCopies, year, isbn, copies, borrowedCopies, author, publisher, id);
        return new RedirectView ("/book");
    }
    
    @PostMapping("/delete/{id}")
    public RedirectView deleteBook(@PathVariable Integer id){
        bookService.delete(id);
        return new RedirectView ("/book");
    }
}
