package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookStoreJpaRepository extends JpaRepository<BookStore, Long> {
     List<BookStore> findAll();
     Optional<BookStore> findById(Long id);
}
