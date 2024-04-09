package org.example.library_management_system.repositories;

import org.example.library_management_system.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepo extends JpaRepository<BorrowingRecord,Long> {
    @Query("SELECT br FROM BorrowingRecord br WHERE br.book.id = :bookId")
    BorrowingRecord findBookId(@Param("bookId") Long bookId);

    @Query("SELECT br FROM BorrowingRecord br WHERE br.patron.id = :patronId")
    BorrowingRecord findPatronId(@Param("patronId") Long patronId);

    @Query("SELECT br FROM BorrowingRecord br WHERE br.patron.id = :patronId AND br.book.id = :bookId")
    BorrowingRecord findByPatronIdAndBookId(@Param("patronId") Long patronId, @Param("bookId") Long bookId);
}
