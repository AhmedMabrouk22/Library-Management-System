package org.example.library_management_system.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.library_management_system.TestUtil;
import org.example.library_management_system.dto.BookDTO;
import org.example.library_management_system.entity.Book;
import org.example.library_management_system.repositories.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    private final MockMvc mockMvc;

    private final BookRepo bookRepo;

    private final ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        bookRepo.deleteAll();
    }

    @Autowired
    public BookControllerTest(MockMvc mockMvc, BookRepo bookRepo) {
        this.mockMvc = mockMvc;
        this.bookRepo = bookRepo;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void itShouldCreateBookAndReturn201StatusCode() throws Exception {
        BookDTO book = TestUtil.createTestBookDTO("978-0-545-01022-1","Book1","author 1","cat1",200,2020);
        String json = objectMapper.writeValueAsString(book);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    void itShouldNotCreateBookAndReturn404StatusCode() throws Exception {
        Book book = TestUtil.createTestBook("978-0-545-01022-1","Book1","author 1","cat1",200,2020);
        bookRepo.save(book);
        BookDTO bookDTO = TestUtil.createTestBookDTO("978-0-545-01022-1","Book1","author 1","cat1",200,2020);
        String json = objectMapper.writeValueAsString(bookDTO);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );
    }

    @Test
    void itShouldNotCreateBookAndReturn404StatusCodeAndMessage() throws Exception {
        BookDTO bookDTO = TestUtil.createTestBookDTO("978-0-545-01022-1","Book1","","cat1",200,2020);
        String json = objectMapper.writeValueAsString(bookDTO);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message").isNotEmpty()
        );
    }

    @Test
    void itShouldReturnListOfBooks() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    void itShouldReturnBookAndReturn200StatusCode() throws Exception {
        Book book = TestUtil.createTestBook("978-0-545-01022-1","Book1","author 1","cat1",200,2020);
        long book_id = bookRepo.save(book).getId();
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/"+book_id)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.data.id").value(book_id)
        );
    }

    @Test
    void itShouldNotReturnBookAndReturn404StatusCode() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/"+200)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    void itShouldDeleteBookAndReturn200StatusCode() throws Exception {
        Book book = TestUtil.createTestBook("978-0-545-01022-1","Book1","author 1","cat1",200,2020);
        long book_id = bookRepo.save(book).getId();
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/books/"+book_id)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    void itShouldNotDeleteBookAndReturn404StatusCode() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/books/"+50)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

}