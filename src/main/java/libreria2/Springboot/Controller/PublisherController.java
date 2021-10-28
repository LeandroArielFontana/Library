package libreria2.Springboot.Controller;

import libreria2.Springboot.Model.Entities.Publisher;
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
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ModelAndView showAll() {
        ModelAndView mav = new ModelAndView("publisher");
        mav.addObject("publishers", publisherService.findAll());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createPublisher() {
        ModelAndView mav = new ModelAndView("publisher-form");
        mav.addObject("publisher", new Publisher());
        mav.addObject("tittle", "Crear Editorial");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPublisher(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("publisher-form");
        mav.addObject("publisher", publisherService.findById(id));
        mav.addObject("tittle", "Editar Editorial");
        mav.addObject("action", "edit");
        return mav;
    }

    @PostMapping("/save")
    public RedirectView savePublisher(@RequestParam String name) {
        publisherService.create(name);
        return new RedirectView("/publisher");
    }

    @PostMapping("/edit")
    public RedirectView updatePublisher(@RequestParam Integer id, @RequestParam String name) {
        publisherService.updateName(id, name);
        return new RedirectView("/publisher");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deletePublisher(@PathVariable Integer id) {
        publisherService.delete(id);
        return new RedirectView("/publisher");
    }

}
