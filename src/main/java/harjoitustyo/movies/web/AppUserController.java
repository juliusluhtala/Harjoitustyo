package harjoitustyo.movies.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import harjoitustyo.movies.domain.AppUserRepository;

@Controller
public class AppUserController {
    @Autowired
    private AppUserRepository appUserRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/appuser/{id}")
        public String showAppUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("appuser", appUserRepository.findById(id));
        return "appuser";
    }
}
