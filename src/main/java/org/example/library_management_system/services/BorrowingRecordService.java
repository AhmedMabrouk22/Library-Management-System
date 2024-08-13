package org.example.library_management_system.services;

import org.example.library_management_system.entity.BorrowingRecord;

public interface BorrowingRecordService {
    BorrowingRecord borrowBook(Long bookId, Long patronId);
    void returnBook(Long bookId, Long patronId);

    BorrowingRecord getBorrowingRecord(Long bookId, Long patronId);
}
