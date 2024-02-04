package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.model.exception.InvalidArgumentsException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {
    public List<Book> findAll() {
        return DataHolder.books;
    }

    public Optional<Book> findById(Long id) {
        return DataHolder.books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    public Optional<Book> findByIsbn(String isbn) {
        return DataHolder.books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    public Optional<Book> save(String isbn, String title, String genre, Integer year, BookStore bookStore) {
        if (bookStore == null) {
            throw new InvalidArgumentsException();
        }
        Book newBook = new Book(isbn, title, genre, year, bookStore);
        DataHolder.books.removeIf(book -> book.getTitle().equals(newBook.getTitle()));
        DataHolder.books.add(newBook);
        return Optional.of(newBook);
    }

    public Optional<Book> edit(Book book, String isbn, String title, String genre, Integer year, BookStore bookStore) {
        if (book == null || bookStore == null) {
            throw new InvalidArgumentsException();
        }
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setGenre(genre);
        book.setYear(year);
        book.setBookStore(bookStore);
        return Optional.of(book);
    }

    public Optional<Author> addAuthorToBook(Optional<Author> optionalAuthor, Optional<Book> optionalBook) {
        if (optionalAuthor.isPresent() && optionalBook.isPresent()) {
            Author author = optionalAuthor.get();
            Book book = optionalBook.get();
            book.getAuthors().removeIf(auth -> auth.getId().equals(author.getId()));
            book.getAuthors().add(author);
            return optionalAuthor;
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        DataHolder.books.removeIf(book -> book.getId().equals(id));
    }

    public void deleteByIsbn(String isbn) {
        DataHolder.books.removeIf(book -> book.getIsbn().equals(isbn));
    }
}
