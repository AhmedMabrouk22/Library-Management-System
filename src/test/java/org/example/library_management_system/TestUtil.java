package org.example.library_management_system;

import org.example.library_management_system.dto.BookDTO;
import org.example.library_management_system.dto.PatronDTO;
import org.example.library_management_system.entity.Book;
import org.example.library_management_system.entity.Patron;
import org.example.library_management_system.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUtil {
    
    public static Book createTestBook(String isbn,String title,String author, String category, Integer pageNumbers, Integer publicationYear){
        return Book.builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .category(category)
                .pageNumbers(pageNumbers)
                .publicationYear(publicationYear)
                .build();
    }

    public static BookDTO createTestBookDTO(String isbn,String title,String author, String category, Integer pageNumbers, Integer publicationYear) {
        return BookDTO.builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .category(category)
                .pageNumbers(pageNumbers)
                .publicationYear(publicationYear)
                .build();
    }

    public static Patron createTestPatron(String name,String email, String phone_number)
    {
        return Patron.builder().name(name).email(email).phone_number(phone_number).build();
    }

    public static PatronDTO createTestPatronDTO(String name, String email, String phone_number)
    {
        return PatronDTO.builder().name(name).email(email).phone_number(phone_number).build();
    }

}
