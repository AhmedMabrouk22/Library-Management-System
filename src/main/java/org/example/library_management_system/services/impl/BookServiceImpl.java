package org.example.library_management_system.services.impl;

import org.example.library_management_system.dto.BookDTO;
import org.example.library_management_system.entity.Book;
import org.example.library_management_system.exceptions.NotFoundException;
import org.example.library_management_system.mappers.Mapper;
import org.example.library_management_system.repositories.BookRepo;
import org.example.library_management_system.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final Mapper<Book, BookDTO> bookMapper;

    public BookServiceImpl(BookRepo bookRepo,Mapper<Book, BookDTO> bookMapper) {
        this.bookRepo = bookRepo;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO create(BookDTO bookDTO) {
        Book book = bookMapper.mapFrom(bookDTO);
        Book newBook = bookRepo.save(book);
        return bookMapper.mapTo(newBook);
    }

    @Override
    public void delete(Long id) {
        if (!isExist(id))
            throw new NotFoundException("Book with id " + id + " not found");
        bookRepo.deleteById(id);
    }

    @Override
    public Optional<BookDTO> findById(Long id) {

        Optional<Book> book = bookRepo.findById(id);
        if (book.isEmpty())
            throw new NotFoundException("Book with id " + id + " not found");
        return book.map(bookMapper::mapTo);
    }

    @Override
    public List<BookDTO> findAll() {
        List<Book> books = new ArrayList<>(bookRepo.findAll());
        return books.stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }

    @Override
    public Page<BookDTO> findAll(Pageable pageable) {
        Page<Book> books = bookRepo.findAll(pageable);
        return books.map(bookMapper::mapTo);
    }

    @Override
    public BookDTO updateById(Long id, BookDTO bookDTO) {
        if (!isExist(id))
            throw new NotFoundException("Book with id " + id + " not found");
        Book book = bookMapper.mapFrom(bookDTO);
        book.setId(id);
        Book updatedBook =  bookRepo.save(book);
        return bookMapper.mapTo(updatedBook);
    }

    @Override
    public boolean isExist(Long id) {
        return bookRepo.existsById(id);
    }
}
