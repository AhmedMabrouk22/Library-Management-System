package org.example.library_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "book_id_seq")
    private Long id;

    @Column(unique = true)
    @NotNull(message = "isbn is required")
    @NotBlank(message = "isbn must not empty")
    @Pattern(regexp = "^(?:ISBN(?:-13)?:?\\s*)?(?=[0-9]{13}$|(?=(?:[0-9]+[-\\s]){4})[-\\s0-9]{17}$|97[89][0-9]{10}$|(?=(?:[0-9]+[-\\s]){3})[-\\s0-9]{13}$)(?:97[89][-\\s]?)?[0-9]{1,5}[-\\s]?[0-9]+[-\\s]?[0-9]+[-\\s]?[0-9]$", message = "Invalid isbn")
    private String isbn;

    @Column(unique = true)
    @NotNull(message = "title is required")
    @NotBlank(message = "title must not empty")
    private String title;
    @NotNull(message = "category is required")
    @NotBlank(message = "category must not empty")
    private String category;
    @NotNull(message = "author is required")
    @NotBlank(message = "author must not empty")
    private String author;
    @NotNull(message = "publication Year is required")
    private Integer publicationYear;
    @NotNull(message = "page numbers is required")
    @Min(value = 10, message = "min is 10")
    private Integer pageNumbers;
}
