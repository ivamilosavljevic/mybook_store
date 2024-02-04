package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAuthors();

    Optional<Author> findById(Long id);

    Optional<Author> saveAuthor(String name, String surname, String biography);

    Optional<Author> editAuthor(Long authorId, String name, String surname, String biography);
}
