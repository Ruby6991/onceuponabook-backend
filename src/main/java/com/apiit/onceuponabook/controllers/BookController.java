package com.apiit.onceuponabook.controllers;

import com.apiit.onceuponabook.dtos.BookDTO;
import com.apiit.onceuponabook.dtos.RatingDTO;
import com.apiit.onceuponabook.models.Book;
import com.apiit.onceuponabook.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/AddBook")
    public ResponseEntity<Boolean> AddBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PostMapping("/GetBook")
    public ResponseEntity<BookDTO> GetBookDetails(@RequestBody Book book){
        return bookService.getBook(book.getId());
    }

    @PostMapping("/GetBookList")
    public ResponseEntity<List<BookDTO>> getBookList(){
        return bookService.getBookList();
    }

    @DeleteMapping("/DeleteBook/{id}")
    public ResponseEntity<Boolean> DeleteBook(@PathVariable int id){
        return bookService.deleteBook(id);
    }

    @PutMapping("/UpdateBook/{id}")
    public ResponseEntity<BookDTO> UpdateBook(@PathVariable int id, @RequestBody Book book){
        return bookService.updateBook(id,book);
    }

    @PostMapping("/GetBookRatingList")
    public ResponseEntity<List<RatingDTO>> getBookRatingList(@RequestBody Book book){
        return bookService.getBookRatingList(book.getId());
    }

}
