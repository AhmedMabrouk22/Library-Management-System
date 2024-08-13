package org.example.library_management_system.controllers;

import org.example.library_management_system.dto.BorrowingRecordDTO;
import org.example.library_management_system.entity.BorrowingRecord;
import org.example.library_management_system.handler.ResponseHandler;
import org.example.library_management_system.services.BorrowingRecordService;
import org.example.library_management_system.utils.HttpStatusMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @PostMapping(path = "api/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<ResponseHandler> borrowBook(@PathVariable(name = "bookId") Long bookId, @PathVariable(name = "patronId") Long patronId)
    {
        BorrowingRecord record = borrowingRecordService.borrowBook(bookId,patronId);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS, HttpStatus.OK,"Book borrow successfully",record.getId());
    }

    @PutMapping(path = "api/return/{bookId}/patron/{patronId}")
    public ResponseEntity<ResponseHandler> returnBook(@PathVariable(name = "bookId") Long bookId, @PathVariable(name = "patronId") Long patronId)
    {
        borrowingRecordService.returnBook(bookId,patronId);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS, HttpStatus.OK,"Book return successfully",null);
    }

}
