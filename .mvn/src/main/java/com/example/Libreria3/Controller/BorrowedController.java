package com.example.Libreria.Controller;

import com.example.Libreria.Entities.Book;
import com.example.Libreria.Entities.Borrowed;
import com.example.Libreria.Entities.Client;
import com.example.Libreria.Exceptions.MyException;
import com.example.Libreria.Service.BookService;
import com.example.Libreria.Service.BorrowedService;
import com.example.Libreria.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/borrowed")
public class BorrowedController {

    @Autowired
    private BorrowedService borrowedService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ModelAndView showAll(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("borrowed");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if(flashMap != null){
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("success", flashMap.get("success"));
        }

        mav.addObject("borrowed", borrowedService.findAll());
        mav.addObject("title", "Prestamos");
        return mav;
    }

    @GetMapping ("/create")
    public ModelAndView createLoan (){
        ModelAndView mav = new ModelAndView("loan-form");
        mav.addObject("borrowed", new Borrowed());
        mav.addObject("books", bookService.availableBooks());
        mav.addObject("clients", clientService.availableClients());
        mav.addObject("title", "Crear Prestamo");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editLoan (@PathVariable Integer id, RedirectAttributes attributes){
        ModelAndView mav = new ModelAndView("loan-form");

        try{
            mav.addObject("borrowed", borrowedService.findById(id));
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/borrowed");
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/borrowed");
        }

        mav.addObject("title", "Editar Prestamo");
        mav.addObject("action", "edit");
        return mav;
    }

    //USAR mav.setViewName

    @PostMapping("/save")
    public RedirectView saveLoan(@RequestParam Book book, @RequestParam Client client, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date borrowingDate, RedirectAttributes attributes){

        try{
            borrowedService.create(book, client, returnDate, borrowingDate);
            attributes.addFlashAttribute("success", "El libro se ha prestado exitosamente!");
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
        }

        return new RedirectView("/borrowed");
    }

    @PostMapping("/edit")
    public RedirectView updateLoan(@RequestParam Integer id, @RequestParam Book book, @RequestParam Client client, @RequestParam Date returnDate, @RequestParam Date borrowingDate, RedirectAttributes attributes){
        try{
            borrowedService.updateBorrowed(id, book, client, returnDate, borrowingDate);
            attributes.addFlashAttribute("success", "El prestamo se ha actualizado exitosamente!");
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView("/borrowed");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteLoan(@PathVariable Integer id){
        borrowedService.deleteLoan(id);
        return new RedirectView("/borrowed");
    }

    @PostMapping("/register/{id}")
    public RedirectView registerLoan(@PathVariable Integer id){
        borrowedService.register(id);
        return new RedirectView("/borrowed");
    }
}