package org.example.library_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String isbn;
    private String title;
    private String category;
    private String author;
    private Integer publicationYear;
    private Integer pageNumbers;
}
