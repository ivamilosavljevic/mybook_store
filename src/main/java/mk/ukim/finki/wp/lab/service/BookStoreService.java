package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.BookStore;

import java.util.List;
import java.util.Optional;

public interface BookStoreService {
    List<BookStore> findAll();

    Optional<BookStore> findBookStoreById(Long id);
}
