package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> saveAuthor(String name, String surname, String biography) {
        return authorRepository.save(name, surname, biography);
    }

    @Override
    public Optional<Author> editAuthor(Long authorId, String name, String surname, String biography) {
        Optional<Author> optionalAuthor = findById(authorId);
        if (optionalAuthor.isEmpty()) {
            return Optional.empty();
        }
        Author author = optionalAuthor.get();
        return this.authorRepository.edit(author, name, surname, biography);
    }
}
