package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();

    Optional<Book> findBookById(Long id);

    Optional<Book> findBookByIsbn(String isbn);

    Optional<Book> saveBook(String isbn, String title, String genre, Integer year, Long bookStoreId);

    Optional<Book> editBook(Long bookId, String isbn, String title, String genre, Integer year, Long bookStoreId);

    Optional<Author> addAuthorToBook(Long id, String isbn);

    void deleteBookById(Long id);

    void deleteBookByIsbn(String isbn);
}
