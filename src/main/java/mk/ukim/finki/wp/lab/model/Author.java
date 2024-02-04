package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String biography;
    private LocalDate dateOfBirth;

    public Author() {
    }

    public Author(String name, String surname, String biography) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }
}
