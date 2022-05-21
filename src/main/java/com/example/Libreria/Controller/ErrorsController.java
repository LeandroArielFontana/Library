package com.example.Libreria.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView errors(HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("error");
        String message1 = "";
        String message2 = "";
        Integer code = response.getStatus();

        switch (code) {

            case 403:
                message1 = "Alto ahi!";
                message2 = "No tienen permisos suficieintes para acceder al recurso solicitado";
                break;

            case 404:
                message1 = "Parece que te perdiste";
                message2 = "El recurso solicitado no fue encontrado";
                break;

            case 500:
                message1 = "Ups..";
                message2 = "Error interno en el servidor";
                break;

            default:
                message1 = "Ups..";
                message2 = "Error inesperado";
                break;
        }

        mav.addObject("message1", message1);
        mav.addObject("message2", message2);
        mav.addObject("code", code);
        mav.addObject("title", "Error");
        return mav;
    }
}
