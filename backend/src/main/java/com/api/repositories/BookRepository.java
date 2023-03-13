package com.api.repositories;

import com.api.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query(nativeQuery = true, value = ("SELECT * FROM book b WHERE b.author = :author AND b.title = :title"))
    Optional<Book> findBookByAuthorAndTitle(String author, String title);

}
