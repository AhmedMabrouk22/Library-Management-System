package org.example.library_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library_management_system.entity.Book;
import org.example.library_management_system.entity.Patron;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecordDTO {
    private Long id;
    private Book book;
    private Patron patron;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
}
