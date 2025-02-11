package pl.gralewicz.kamil.java.app.notebook.notebook.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.gralewicz.kamil.java.app.notebook.notebook.controller.model.Author;
import pl.gralewicz.kamil.java.app.notebook.notebook.repository.entity.AuthorEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class AuthorMapper {

    private static final Logger LOGGER = Logger.getLogger(AuthorMapper.class.getName());

    public List<Author> fromEntities(List<AuthorEntity> authorEntities) {
        LOGGER.info("fromEntities()");

        List<Author> authors = new ArrayList<>();

        for (AuthorEntity authorEntity : authorEntities) {
            Author author = from(authorEntity);
            authors.add(author);
        }

        LOGGER.info("fromEntities(...)= ");
        return authors;
    }

    public AuthorEntity from(Author author) {
        LOGGER.info("from(" + author + ")");
        ModelMapper modelMapper = new ModelMapper();
        AuthorEntity authorEntity = modelMapper.map(author, AuthorEntity.class);
        LOGGER.info("from(...)= " + authorEntity);
        return authorEntity;
    }

    public Author from(AuthorEntity authorEntity) {
        LOGGER.info("from(" + authorEntity + ")");
        ModelMapper modelMapper = new ModelMapper();
        Author author = modelMapper.map(authorEntity, Author.class);
        LOGGER.info("from(...)= " + author);
        return author;
    }

}
