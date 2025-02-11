package pl.gralewicz.kamil.java.app.notebook.notebook.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import pl.gralewicz.kamil.java.app.notebook.notebook.controller.model.Note;
import pl.gralewicz.kamil.java.app.notebook.notebook.repository.entity.NoteEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class NoteMapper {

    private static final Logger LOGGER = Logger.getLogger(NoteMapper.class.getName());

    public List<Note> fromEntities(List<NoteEntity> noteEntities) {
        LOGGER.info("fromEntities()");

        List<Note> notes = new ArrayList<>();

        for (NoteEntity noteEntity : noteEntities) {
            Note note = from(noteEntity);
            notes.add(note);
        }

        LOGGER.info("fromEntities()");
        return notes;
    }

    public NoteEntity from(Note note) {
        LOGGER.info("from(" + note + ")");
        ModelMapper modelMapper = new ModelMapper();
        NoteEntity noteEntity = modelMapper.map(note, NoteEntity.class);
        LOGGER.info("from(...)= " + noteEntity);
        return noteEntity;
    }

    public Note from(NoteEntity noteEntity) {
        LOGGER.info("from(" + noteEntity + ")");
        ModelMapper modelMapper = new ModelMapper();
        Note note = modelMapper.map(noteEntity, Note.class);
        LOGGER.info("from(...)= " + note);
        return note;
    }
}
