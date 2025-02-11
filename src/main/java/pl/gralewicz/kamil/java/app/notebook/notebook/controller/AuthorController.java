package pl.gralewicz.kamil.java.app.notebook.notebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gralewicz.kamil.java.app.notebook.notebook.controller.model.Author;
import pl.gralewicz.kamil.java.app.notebook.notebook.service.AuthorService;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/authors")
public class AuthorController {
    private static final Logger LOGGER = Logger.getLogger(AuthorController.class.getName());

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String list(ModelMap modelMap) {
        LOGGER.info("list()");
        List<Author> authors = authorService.list();
        modelMap.addAttribute("authors", authors);
        LOGGER.info("list(...)=" + authors);
        return "authors";
    }

    @GetMapping(value = "/create")
    public String createView(ModelMap modelMap) {
        LOGGER.info("createView()");
        modelMap.addAttribute("createMessage", "Fill out the form fields");
        modelMap.addAttribute("authors", new Author());
        modelMap.addAttribute("isEdit", false);
        LOGGER.info("createView(...)= ");
        return "author-create.html";
    }

    @PostMapping
    public String create(Author author) {
        LOGGER.info("create(" + author + ")");
        Author createdAuthor = authorService.create(author);
        LOGGER.info("create(...)= ");
        return "redirect:/authors";
    }

    @GetMapping(value = "/id")
    public String read(@PathVariable Long id, String firstName, String lastName, ModelMap modelMap) {
        LOGGER.info("read(" + id + ")");
        LOGGER.info("read(" + firstName + ")");
        LOGGER.info("read(" + lastName + ")");
        Author readAuthor = authorService.read(id);
        modelMap.addAttribute("createMessage", "This is author: " + readAuthor);
        LOGGER.info("read(...)= ");
        return "author-read.html";
    }

    @GetMapping(value = "/update/{id}")
    public String updateView(@PathVariable Long id, ModelMap modelMap) {
        LOGGER.info("updateView()");
        Author readAuthor = authorService.read(id);
        LOGGER.info("updateView()" + readAuthor);
        return "author-create";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, String firstName, String lastName, ModelMap modelMap) {
        LOGGER.info("delete(" + id + ")");
        LOGGER.info("delete(" + firstName + ")");
        LOGGER.info("delete(" + lastName + ")");
        authorService.delete(id);
        return "redirect:authors";
    }
}
