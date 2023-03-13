package com.api.services.impl;

import com.api.dtos.BookDto;
import com.api.dtos.BookPagination;
import com.api.exceptions.BookAlreadyExistException;
import com.api.exceptions.BookNotFoundException;
import com.api.models.Book;
import com.api.repositories.BookRepository;
import com.api.services.BookService;
import com.api.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
        checkBookAlreadyExist(bookDto.getAuthor().toLowerCase(), bookDto.getTitle().toLowerCase());
        Book newBook = new Book();
        mapToEntity(bookDto, newBook);
        return bookRepository.save(newBook);
    }

    @Override
    public Book updateBook(BookDto bookDto, UUID id) {
        Book searchedBook = getBookDetailsById(id);
        checkBookAlreadyExist(bookDto.getAuthor().toLowerCase(), bookDto.getTitle().toLowerCase(), id);
        mapToEntity(bookDto, searchedBook);
        return bookRepository.save(searchedBook);
    }

    @Override
    public Book getBookDetailsById(UUID id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Not Found"));
    }

    @Override
    public void deleteBookById(UUID id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> getBookDetailsByAuthorAndTitle(String author, String title) {
        return bookRepository.findBookByAuthorAndTitle(author,title);
    }

    @Override
    public BookPagination getAllBooks(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Book> books = bookRepository.findAll(pageable);
        List<Book> listOfBooks = books.getContent();

        BookPagination bookPagination = new BookPagination();
        bookPagination.setContent(listOfBooks);
        bookPagination.setPageNo(books.getNumber());
        bookPagination.setPageSize(books.getSize());
        bookPagination.setTotalElements(books.getTotalElements());
        bookPagination.setTotalPages(books.getTotalPages());
        bookPagination.setLast(books.isLast());
        return bookPagination;
    }

    private void mapToEntity(BookDto bookDto, Book book){
        book.setTitle(bookDto.getTitle().toLowerCase());
        book.setAuthor(bookDto.getAuthor().toLowerCase());
        book.setPagesNumber(bookDto.getPagesNumber());
        bookDto.getGenders().forEach(gender -> genderService.getGenderDetails(gender.getId()));
        book.setGenders(bookDto.getGenders());
    }

    private void checkBookAlreadyExist(String author, String title){
        boolean checkExist = getBookDetailsByAuthorAndTitle(author, title).isPresent();
        if(checkExist){
            throw new BookAlreadyExistException("This book already exists");
        }
    }

    private void checkBookAlreadyExist(String author, String title, UUID id){
        Optional<Book> search = getBookDetailsByAuthorAndTitle(author, title);
        boolean checkExist = search.isPresent() && !search.get().getId().equals(id);
        if(checkExist){
            throw new BookAlreadyExistException("This book already exists");
        }
    }
}
