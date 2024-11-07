package harjoitustyo.movies.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import harjoitustyo.movies.domain.Category;
import harjoitustyo.movies.domain.CategoryRepository;

@Controller

public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/categorylist", method=RequestMethod.GET)
    public String categoryList(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/savecategory", method=RequestMethod.POST)
        public String save(Category category){
        categoryRepository.save(category);
        return "redirect:/categorylist";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/addcategory")
        public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "addcategory";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/deletecategory/{id}", method=RequestMethod.GET)
        public String deleteCategory(@PathVariable("id") Long categoryId, Model model) {
        categoryRepository.deleteById(categoryId);
        return "redirect:/categorylist";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/editcategory/{id}")
        public String showModCategory(@PathVariable("id") Long categoryId, Model model){
        model.addAttribute("category", categoryRepository.findById(categoryId));
        return "editcategory";
    }
}
