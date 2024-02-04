package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Author> authors;
    public static List<Book> books;
    public static List<BookStore> bookStores;

    @PostConstruct
    public void initAuthors() {
        authors = new ArrayList<>();
        authors.add(new Author("Masha", "Markovska", "Masha's Biography"));
        authors.add(new Author("Marko", "Markovski", "Marko's Biography"));
        authors.add(new Author("Eva", "Petkovikj", "Eva's Biography"));
        authors.add(new Author("Vasilaki", "Konstatinopolos", "Vasilaki's Biography"));
    }

    @PostConstruct
    public void initBooks() {
        books = new ArrayList<>();
        bookStores = new ArrayList<>();

        BookStore store = new BookStore("Literatura", "Skopje", "Partizanska");
        BookStore store1 = new BookStore("Biblioteka", "Kumanovo", "Lesninska");
        BookStore store2 = new BookStore("Akademska", "Skopje", "Partizanska");

        bookStores.add(store);
        bookStores.add(store1);
        bookStores.add(store2);

        books.add(new Book("12345", "Book 1", "Tragedy", 1989, store));
        books.add(new Book("13579", "Book 2", "Comedy", 1969, store1));
        books.add(new Book("56789", "Book 3", "Drama", 2013, store2));
    }
}
