package com.api.services.impl;

import com.api.dtos.BookDto;
import com.api.dtos.BookPagination;
import com.api.exceptions.BookNotFoundException;
import com.api.exceptions.GenderNotFoundException;
import com.api.models.Book;
import com.api.repositories.BookRepository;
import com.api.services.BookService;
import com.api.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;
    final GenderService genderService;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository, GenderService genderService) {
        this.bookRepository = bookRepository;
        this.genderService = genderService;
    }

    @Override
    public Book createBook(BookDto bookDto) {
        Book newBook = new Book();
        mapToEntity(bookDto, newBook);
        return bookRepository.save(newBook);
    }

    @Override
    public Book updateBook(BookDto bookDto, UUID id) {
        Book searchedBook = getBookDetails(id).orElseThrow(() -> new BookNotFoundException("Not Found"));
        mapToEntity(bookDto, searchedBook);
        return bookRepository.save(searchedBook);
    }

    @Override
    public Optional<Book> getBookDetails(UUID id) {
        return bookRepository.findById(id);
    }

    @Override
    public BookPagination getAllBooks(int pageNum, int pageSize) {
        return null;
    }

    private void mapToEntity(BookDto bookDto, Book book){
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPagesNumber(bookDto.getPagesNumber());
        bookDto.getGenders().forEach(gender -> genderService.getGenderDetails(gender.getId())
                .orElseThrow(() -> new GenderNotFoundException("Contain a gender not existent")));
        book.setGenders(bookDto.getGenders());
    }
}
