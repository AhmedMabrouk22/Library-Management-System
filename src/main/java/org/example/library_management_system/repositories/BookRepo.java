package org.example.library_management_system.repositories;

import org.example.library_management_system.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> , PagingAndSortingRepository<Book,Long> {
}
