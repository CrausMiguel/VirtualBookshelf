package com.api.services;

import com.api.dtos.BookDto;
import com.api.dtos.BookPagination;
import com.api.models.Book;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
public interface BookService {
    Book createBook(BookDto bookDto);
    Book updateBook(BookDto bookDto, UUID id);
    Book getBookDetailsById(UUID id);
    void deleteBookById(UUID id);
    Optional<Book> getBookDetailsByAuthorAndTitle(String author, String title);
    BookPagination getAllBooks(int pageNum, int pageSize);
}
