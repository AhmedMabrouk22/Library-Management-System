package org.example.library_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "borrowing_records")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @NotNull(message = "Book id is required")
    private Book book;

    @ManyToOne
    @NotNull(message = "Patron id is required")
    private Patron patron;

    private LocalDate borrowingDate;
    @NotNull(message = "return date is required")
    @DateTimeFormat
    private LocalDate returnDate;
}
