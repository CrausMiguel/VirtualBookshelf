package com.api.controllers;

import com.api.dtos.BookDto;
import com.api.dtos.BookPagination;
import com.api.models.Book;
import com.api.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<Book> createBook(@RequestBody BookDto newBook){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(newBook));
    }

    @PutMapping("")
    public ResponseEntity<Book> updateBook(@RequestBody BookDto bookUpdate, @RequestParam(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(bookUpdate, id));
    }

    @GetMapping("")
    public ResponseEntity<BookPagination> getAllBooks(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue= "10", required = false) int pageSize){
        return ResponseEntity.status(HttpStatus.FOUND).body(bookService.getAllBooks(pageNum, pageSize));
    }

    @GetMapping("/detail")
    public ResponseEntity<Book> getBookDetails(@RequestParam(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.FOUND).body(bookService.getBookDetailsById(id));
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteBook(@RequestParam(value = "id")UUID id){
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }
}
