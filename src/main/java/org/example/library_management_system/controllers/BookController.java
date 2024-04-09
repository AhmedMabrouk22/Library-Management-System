package org.example.library_management_system.controllers;

import jakarta.validation.Valid;
import org.example.library_management_system.dto.BookDTO;
import org.example.library_management_system.entity.Book;
import org.example.library_management_system.handler.ResponseHandler;
import org.example.library_management_system.mappers.Mapper;
import org.example.library_management_system.services.BookService;
import org.example.library_management_system.utils.HttpStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/books")
public class BookController {
     private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping(path = "")
    public ResponseEntity<ResponseHandler> create(@RequestBody @Valid BookDTO bookDTO) {
        BookDTO newBook = bookService.create(bookDTO);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS,HttpStatus.CREATED,"Book Created Successfully",newBook );
    }

    @GetMapping(path = "")
    public  ResponseEntity<ResponseHandler> getAll(@RequestParam(name = "page" ,defaultValue = "0") Integer page, @RequestParam(name = "limit", defaultValue = "15") Integer limit) {
        Pageable pageable = PageRequest.of(page,limit);
        Page<BookDTO> books = bookService.findAll(pageable);
        System.out.println(books);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS,HttpStatus.OK,"Books Get Successfully",books.getContent());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseHandler> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS,HttpStatus.OK,"Book Deleted Successfully", null);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseHandler> getById(@PathVariable Long id) {
        Optional<BookDTO> book = bookService.findById(id);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS,HttpStatus.OK,"Book Get Successfully", book);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseHandler> update(@PathVariable Long id, @RequestBody @Valid BookDTO bookDTO) {
        BookDTO book = bookService.updateById(id,bookDTO);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS,HttpStatus.OK,"Book Updated successfully",book);
    }
}
