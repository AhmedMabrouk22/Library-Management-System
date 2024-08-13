package org.example.library_management_system.services.impl;

import org.example.library_management_system.entity.Book;
import org.example.library_management_system.entity.BorrowingRecord;
import org.example.library_management_system.entity.Patron;
import org.example.library_management_system.exceptions.BorrowingIsNotAllowedException;
import org.example.library_management_system.exceptions.NotFoundException;
import org.example.library_management_system.repositories.BookRepo;
import org.example.library_management_system.repositories.BorrowingRecordRepo;
import org.example.library_management_system.repositories.PatronRepo;
import org.example.library_management_system.services.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    private final BorrowingRecordRepo borrowingRecordRepo;

    public BorrowingRecordServiceImpl(BorrowingRecordRepo borrowingRecordRepo) {
        this.borrowingRecordRepo = borrowingRecordRepo;
    }

    @Override
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepo.findBookId(bookId);
        if (record != null) {
            throw new BorrowingIsNotAllowedException("Book are not available");
        }

        record = borrowingRecordRepo.findPatronId(patronId);
        if (record != null) {
            throw new BorrowingIsNotAllowedException("Patron can only borrow on book");
        }

        BorrowingRecord newRecord = new BorrowingRecord();
        newRecord.setBook(Book.builder().id(bookId).build());
        newRecord.setPatron(Patron.builder().id(patronId).build());
        LocalDate curDate = LocalDate.now();
        newRecord.setBorrowingDate(curDate);
        newRecord.setReturnDate(curDate.plusDays(10));
        return borrowingRecordRepo.save(newRecord);
    }

    @Override
    public void returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = getBorrowingRecord(bookId,patronId);
        if (record == null) {
            throw new NotFoundException("This record not found");
        }

        borrowingRecordRepo.deleteById(record.getId());
    }

    @Override
    public BorrowingRecord getBorrowingRecord(Long bookId, Long patronId) {
        return borrowingRecordRepo.findByPatronIdAndBookId(bookId,patronId);
    }


}
