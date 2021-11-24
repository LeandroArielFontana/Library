package com.example.Libreria3.Controller;

import com.example.Libreria3.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView create(){
        ModelAndView mav = new ModelAndView("role-form");
        mav.addObject("title", "Crear Rol");
        return mav;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView save (@RequestParam String role){
        roleService.create(role);
        return new RedirectView("/");
    }
}
