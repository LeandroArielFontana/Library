package com.example.Libreria.Controller;

import com.example.Libreria.Entities.Client;
import com.example.Libreria.Entities.Role;
import com.example.Libreria.Exceptions.MyException;
import com.example.Libreria.Service.ClientService;
import com.example.Libreria.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ModelAndView showAll(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("client");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("success", flashMap.get("success"));
        }

        mav.addObject("clients", clientService.findAll());
        mav.addObject("title", "Clientes");
        return mav;
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView createClient() {
        ModelAndView mav = new ModelAndView("client-form");
        mav.addObject("client", new Client());
        mav.addObject("title", "Crear Cliente");
        mav.addObject("action", "save");
        mav.addObject("roles", roleService.findAll());
        return mav;
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView editClient(@PathVariable Integer id, RedirectAttributes attributes) {
        ModelAndView mav = new ModelAndView("client-form");

        try {
            mav.addObject("client", clientService.findById(id));
        } catch (MyException e) {
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/client");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/client");
        }
        mav.addObject("roles", roleService.findAll());
        mav.addObject("title", "Editar Cliente");
        mav.addObject("action", "edit");
        return mav;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveClient(@RequestParam String name, @RequestParam String lastName, @RequestParam Long dni, @RequestParam String phoneNumber, @RequestParam("userSecurity.username") String username, @RequestParam("userSecurity.password") String password, @RequestParam("userSecurity.role") Role role, RedirectAttributes attributes) {
        try {
            clientService.create(dni, name, lastName, phoneNumber, username, password, role);
            attributes.addFlashAttribute("success", "El cliente ha sido creado exitosamente!");
        } catch (MyException e) {
            attributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView("/client");
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView updateClient(@RequestParam Integer id, @RequestParam String name, @RequestParam String lastName, @RequestParam Long dni, @RequestParam String phoneNumber, @RequestParam Role role, RedirectAttributes attributes) {
        try {
            clientService.update(id, name, lastName, phoneNumber, dni);
            attributes.addFlashAttribute("success", "El cliente ha sido actualizado exitosamente!");
        } catch (MyException e) {
            attributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView("/client");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView deleteClient(@PathVariable Integer id) {
        clientService.delete(id);
        return new RedirectView("/client");
    }

    @PostMapping("/register/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView registerClient(@PathVariable Integer id) {
        clientService.register(id);
        return new RedirectView("/client");
    }
}
