package pl.gralewicz.kamil.java.app.notebook.notebook.service;

import org.springframework.stereotype.Service;
import pl.gralewicz.kamil.java.app.notebook.notebook.controller.model.Note;
import pl.gralewicz.kamil.java.app.notebook.notebook.repository.NoteRepository;
import pl.gralewicz.kamil.java.app.notebook.notebook.repository.entity.NoteEntity;
import pl.gralewicz.kamil.java.app.notebook.notebook.service.mapper.NoteMapper;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class NoteService {
    private static final Logger LOGGER = Logger.getLogger(NoteService.class.getName());

    private NoteRepository noteRepository;
    private NoteMapper noteMapper;

    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public List<Note> list() {
        LOGGER.info("list()");
        List<NoteEntity> noteEntities = noteRepository.findAll();
        List<Note> notes = noteMapper.fromEntities(noteEntities);
        LOGGER.info("list(...)= " + notes);
        return notes;
    }

    public Note create(Note note) {
        LOGGER.info("create()");
        NoteEntity noteEntity = noteMapper.from(note);
        NoteEntity createdNoteEntity = noteRepository.save(noteEntity);
        Note mappedNote = noteMapper.from(createdNoteEntity);
        LOGGER.info("create(...)= " + mappedNote);
        return mappedNote;
    }

    public Note read(Long id) {
        LOGGER.info("read(" + id + ")");
        Optional<NoteEntity> optionalNoteEntity = noteRepository.findById(id);
        NoteEntity noteEntity = optionalNoteEntity.orElseThrow();
        LOGGER.info("read(...)= ");
        return null;
    }

    public void delete(Long id) {
        LOGGER.info("delete(" + id + ")");
        noteRepository.deleteById(id);
        LOGGER.info("delete(...)= ");
    }
}
