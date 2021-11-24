package com.example.Libreria3.Controller;

import com.example.Libreria3.Entities.Publisher;
import com.example.Libreria3.Exceptions.MyException;
import com.example.Libreria3.Service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ModelAndView showAll(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("publisher");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if(flashMap != null){
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("publishers", publisherService.findAll());
        mav.addObject("title", "Editoriales");
        return mav;
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView createPublisher() {
        ModelAndView mav = new ModelAndView("publisher-form");
        mav.addObject("publisher", new Publisher());
        mav.addObject("title", "Crear Editorial");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView editPublisher(@PathVariable Integer id, RedirectAttributes attributes) {
        ModelAndView mav = new ModelAndView("publisher-form");

        try {
            mav.addObject("publisher", publisherService.findById(id));
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/publisher");
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/publisher");
        }

        mav.addObject("title", "Editar Editorial");
        mav.addObject("action", "edit");
        return mav;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView savePublisher(@RequestParam String name, RedirectAttributes attributes) {

        try{
            publisherService.create(name);
            attributes.addFlashAttribute("success", "La ediotrial ha sido creado exitosamente!");
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
        }

        return new RedirectView("/publisher");
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView updatePublisher(@RequestParam Integer id, @RequestParam String name, RedirectAttributes attributes) {

        try{
            publisherService.updateName(id, name);
            attributes.addFlashAttribute("success", "La editorial ha sido editada exitosamente!");
        }catch (MyException e){
            attributes.addFlashAttribute("error", e.getMessage());
        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
        }

        return new RedirectView("/publisher");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView deletePublisher(@PathVariable Integer id) {
        publisherService.delete(id);
        return new RedirectView("/publisher");
    }

    @PostMapping("/register/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView register (@PathVariable Integer id) {
        publisherService.register(id);
        return new RedirectView("/publisher");
    }
}

