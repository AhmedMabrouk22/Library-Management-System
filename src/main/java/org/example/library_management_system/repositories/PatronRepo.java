package org.example.library_management_system.repositories;

import org.example.library_management_system.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepo extends JpaRepository <Patron,Long> , PagingAndSortingRepository<Patron,Long> {
}
