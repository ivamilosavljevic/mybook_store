package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.exception.InvalidArgumentsException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {
    public List<Author> findAll() {
        return DataHolder.authors;
    }

    public Optional<Author> findById(Long id) {
        return DataHolder.authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }

    public Optional<Author> save(String name, String surname, String biography) {
        Author author = new Author(name, surname, biography);
        DataHolder.authors.removeIf(a -> a.getName().equals(author.getName())
        && a.getSurname().equals(author.getSurname()));
        DataHolder.authors.add(author);
        return Optional.of(author);
    }

    public Optional<Author> edit(Author author, String name, String surname, String biography) {
        if (author == null) {
            throw new InvalidArgumentsException();
        }
        author.setName(name);
        author.setSurname(surname);
        author.setBiography(biography);
        return Optional.of(author);
    }
}
