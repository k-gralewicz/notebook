package pl.gralewicz.kamil.java.app.notebook.notebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gralewicz.kamil.java.app.notebook.notebook.controller.model.Note;
import pl.gralewicz.kamil.java.app.notebook.notebook.service.NoteService;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/notes")
public class NoteController {
    private static final Logger LOGGER = Logger.getLogger(NoteController.class.getName());

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String list(ModelMap modelMap) {
        LOGGER.info("list()");
        List<Note> notes = noteService.list();
        modelMap.addAttribute("notes", notes);
        LOGGER.info("list(...)=" + notes);
        return "notes";
    }

    @GetMapping(value = "/create")
    public String createView(ModelMap modelMap) {
        LOGGER.info("createView()");
        modelMap.addAttribute("createMessage", "Fill out the form fields");
        modelMap.addAttribute("notes", new Note());
        modelMap.addAttribute("isEdit", false);
        LOGGER.info("createView(...)= ");
        return "note-create.html";
    }

    @PostMapping
    public String create(Note note) {
        LOGGER.info("create(" + note + ")");
        Note createdNote = noteService.create(note);
        LOGGER.info("create(...)= ");
        return "redirect:/notes";
    }

    @GetMapping(value = "/id")
    public String read(@PathVariable Long id, String title , String content, ModelMap modelMap) {
        LOGGER.info("read(" + id + ")");
        LOGGER.info("read(" + title + ")");
        LOGGER.info("read(" + content + ")");
        Note readNote = noteService.read(id);
        modelMap.addAttribute("createMessage", "This is note: " + readNote);
        LOGGER.info("read(...)= ");
        return "note-read.html";
    }

    @GetMapping(value = "/update/{id}")
    public String updateView(@PathVariable Long id, ModelMap modelMap) {
        LOGGER.info("updateView()");
        Note readNote = noteService.read(id);
        LOGGER.info("updateView()" + readNote);
        return "note-create";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, String title, String content, ModelMap modelMap) {
        LOGGER.info("delete(" + id + ")");
        LOGGER.info("delete(" + title + ")");
        LOGGER.info("delete(" + content + ")");
        noteService.delete(id);
        return "redirect:notes";
    }
}