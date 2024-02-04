package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.BookStoreRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookStoreRepository bookStoreRepository;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, BookStoreRepository bookStoreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Optional<Book> saveBook(String isbn, String title, String genre, Integer year, Long bookStoreId) {
        Optional<BookStore> optionalBookStore = bookStoreRepository.findById(bookStoreId);
        if (optionalBookStore.isEmpty()) {
            return Optional.empty();
        }
        BookStore bookStore = optionalBookStore.get();
        return this.bookRepository.save(isbn, title, genre, year, bookStore);
    }

    @Override
    public Optional<Book> editBook(Long bookId, String isbn, String title, String genre, Integer year, Long bookStoreId) {
        Optional<Book> optionalBook = findBookById(bookId);
        Optional<BookStore> optionalBookStore = bookStoreRepository.findById(bookStoreId);
        if (optionalBook.isEmpty() || optionalBookStore.isEmpty()) {
            return Optional.empty();
        }
        Book book = optionalBook.get();
        BookStore bookStore = optionalBookStore.get();
        return this.bookRepository.edit(book, isbn, title, genre, year, bookStore);
    }

    @Override
    public Optional<Author> addAuthorToBook(Long id, String isbn) {
        Optional<Book> book = findBookByIsbn(isbn);
        Optional<Author> author = authorRepository.findById(id);

        return bookRepository.addAuthorToBook(author, book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteBookByIsbn(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }
}
