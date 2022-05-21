package com.example.Libreria.Controller;

import com.example.Libreria.Entities.Client;
import com.example.Libreria.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("login");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (error != null) {
            mav.addObject("error", "Usuario o Contraseña invalida");
        } else if (logout != null) {
            mav.addObject("logout", "Ha salido correctamente de la plataforma");
        } else if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("dni", flashMap.get("dni"));
            mav.addObject("name", flashMap.get("name"));
            mav.addObject("lastName", flashMap.get("lastName"));
            mav.addObject("phoneNumber", flashMap.get("phoneNumber"));
            mav.addObject("username", flashMap.get("username"));
            mav.addObject("password", flashMap.get("password"));
        }

        if (principal != null) {
            mav.setViewName("redirect:/"); //Si esta logueado lo redirijo al index siempre!
        }

        mav.addObject("client", new Client());
        mav.addObject("title", "Login");
        mav.addObject("action", "singUp");

        return mav;
    }
}
