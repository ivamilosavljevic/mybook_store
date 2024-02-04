package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.repository.jpa.BookStoreJpaRepository;
import mk.ukim.finki.wp.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    private final BookStoreJpaRepository bookStoreJpaRepository;

    public BookStoreServiceImpl(BookStoreJpaRepository bookStoreJpaRepository) {
        this.bookStoreJpaRepository = bookStoreJpaRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreJpaRepository.findAll();
    }

    @Override
    public Optional<BookStore> findBookStoreById(Long id) {
        return bookStoreJpaRepository.findById(id);
    }
}
