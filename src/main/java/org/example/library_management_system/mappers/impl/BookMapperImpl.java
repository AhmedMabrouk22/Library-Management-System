package org.example.library_management_system.mappers.impl;

import org.example.library_management_system.dto.BookDTO;
import org.example.library_management_system.entity.Book;
import org.example.library_management_system.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<Book, BookDTO> {



    private final ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDTO mapTo(Book book) {
        return modelMapper.map(book,BookDTO.class);
    }

    @Override
    public Book mapFrom(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

}
