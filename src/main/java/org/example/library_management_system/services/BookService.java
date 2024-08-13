package org.example.library_management_system.services;

import org.example.library_management_system.dto.BookDTO;
import org.example.library_management_system.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookDTO create(BookDTO bookDTO);

    void delete(Long id);
    Optional<BookDTO> findById(Long id);
    List<BookDTO> findAll();

    Page<BookDTO> findAll(Pageable pageable);
    BookDTO updateById(Long id, BookDTO bookDTO);

    boolean isExist(Long id);

}
