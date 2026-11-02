package com.example.book_management.controller;

import com.example.book_management.dto.BookCreateDTO;
import com.example.book_management.entity.Book;
import com.example.book_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@ModelAttribute BookCreateDTO dto) {
        Book savedBook = bookService.createBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
}