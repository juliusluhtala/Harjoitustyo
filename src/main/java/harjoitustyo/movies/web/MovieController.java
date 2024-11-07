package harjoitustyo.movies.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import harjoitustyo.movies.domain.CategoryRepository;
import harjoitustyo.movies.domain.Movie;
import harjoitustyo.movies.domain.MovieRepository;

@Controller
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
    @RequestMapping(value = "/movielist", method=RequestMethod.GET)
    public String movieList(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "movielist";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/deletemovie/{id}", method=RequestMethod.GET)
        public String deleteMovie(@PathVariable("id") Long movieId, Model model) {
        movieRepository.deleteById(movieId);
        return "redirect:/movielist";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/addmovie")
        public String addMovie(Model model){
        model.addAttribute("movie", new Movie());
        model.addAttribute("category", categoryRepository.findAll());
        return "addmovie";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/savemovie", method=RequestMethod.POST)
        public String save(Movie movie){
        movieRepository.save(movie);
        return "redirect:/movielist";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/editmovie/{id}")
        public String showModMovie(@PathVariable("id") Long movieId, Model model){
        model.addAttribute("movie", movieRepository.findById(movieId));
        model.addAttribute("category", categoryRepository.findAll());
        return "editmovie";
    }
}
