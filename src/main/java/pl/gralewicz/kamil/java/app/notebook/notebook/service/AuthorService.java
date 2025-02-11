package pl.gralewicz.kamil.java.app.notebook.notebook.service;

import org.springframework.stereotype.Service;
import pl.gralewicz.kamil.java.app.notebook.notebook.controller.model.Author;
import pl.gralewicz.kamil.java.app.notebook.notebook.repository.AuthorRepository;
import pl.gralewicz.kamil.java.app.notebook.notebook.repository.entity.AuthorEntity;
import pl.gralewicz.kamil.java.app.notebook.notebook.service.mapper.AuthorMapper;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AuthorService {
    private static final Logger LOGGER = Logger.getLogger(AuthorService.class.getName());

    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<Author> list() {
        LOGGER.info("list()");
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        List<Author> authors = authorMapper.fromEntities(authorEntities);
        LOGGER.info("list(...)= " + authors);
        return authors;
    }

    public Author create(Author author) {
        LOGGER.info("create()");
        AuthorEntity authorEntity = authorMapper.from(author);
        AuthorEntity createdAuthorEntity = authorRepository.save(authorEntity);
        Author mappedAuthor = authorMapper.from(createdAuthorEntity);
        LOGGER.info("create()" + mappedAuthor);
        return mappedAuthor;
    }

    public Author read(Long id) {
        LOGGER.info("read(" + id + ")");
        Optional<AuthorEntity> optionalAuthorEntity = authorRepository.findById(id);
        AuthorEntity authorEntity = optionalAuthorEntity.orElseThrow();
        LOGGER.info("read(...)= ");
        return null;
    }

    public void delete(Long id) {
        LOGGER.info("delete(" + id + ")");
        authorRepository.deleteById(id);
        LOGGER.info("delete(...)= ");
    }

}
