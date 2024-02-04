package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private Integer year;
    @OneToMany
    private List<Author> authors;
    @ManyToOne
    private BookStore bookStore;

    public Book() {
    }

    public Book(String isbn, String title, String genre, Integer year, BookStore bookStore) {
        this.id = (long) (Math.random() * 1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = new ArrayList<>();
        this.bookStore = bookStore;
    }
}
