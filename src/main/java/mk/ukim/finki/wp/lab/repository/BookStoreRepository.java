package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookStoreRepository {
    public List<BookStore> findAll() {
        return DataHolder.bookStores;
    }

    public Optional<BookStore> findById(Long id) {
        return DataHolder.bookStores.stream()
                .filter(store -> store.getId().equals(id))
                .findFirst();
    }
}
