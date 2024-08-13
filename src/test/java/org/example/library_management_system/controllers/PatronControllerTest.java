package org.example.library_management_system.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.library_management_system.TestUtil;
import org.example.library_management_system.dto.PatronDTO;
import org.example.library_management_system.entity.Patron;
import org.example.library_management_system.repositories.PatronRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PatronControllerTest {
    private final MockMvc mockMvc;

    private final PatronRepo patronRepo;

    private final ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        patronRepo.deleteAll();
    }

    @Autowired
    public PatronControllerTest(MockMvc mockMvc, PatronRepo patronRepo) {
        this.mockMvc = mockMvc;
        this.patronRepo = patronRepo;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void itShouldCreatePatronAndReturn201StatusCode() throws Exception {
        PatronDTO patronDTO = TestUtil.createTestPatronDTO("ahmed","ahmed@ahmed.com","+1234567890");
        String json = objectMapper.writeValueAsString(patronDTO);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    void itShouldNotCreatePatronAndReturn404StatusCode() throws Exception {
        Patron patron = TestUtil.createTestPatron("ahmed","ahmed@ahmed.com","+1234567890");
        patronRepo.save(patron);
        PatronDTO patronDTO = TestUtil.createTestPatronDTO("ahmed","ahmed@ahmed.com","+1234567890");
        String json = objectMapper.writeValueAsString(patronDTO);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );
    }

    @Test
    void itShouldNotCreatePatronAndReturn404StatusCodeAndMessage() throws Exception {
        PatronDTO patronDTO = TestUtil.createTestPatronDTO("ahmed","ahmedahmed.com","+1234567890");
        String json = objectMapper.writeValueAsString(patronDTO);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message").isNotEmpty()
        );
    }

    @Test
    void itShouldReturnListOfPatrons() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    void itShouldReturnPatronAndReturn200StatusCode() throws Exception {
        Patron patron = TestUtil.createTestPatron("ahmed","ahmed@ahmed.com","+1234567890");
        long patron_id = patronRepo.save(patron).getId();
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons/"+patron_id)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.data.id").value(patron_id)
        );
    }

    @Test
    void itShouldNotReturnPatronAndReturn404StatusCode() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons/"+200)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    void itShouldDeletePatronAndReturn200StatusCode() throws Exception {
        Patron patron = TestUtil.createTestPatron("ahmed","ahmed@ahmed.com","+1234567890");
        long patron_id = patronRepo.save(patron).getId();
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/patrons/"+patron_id)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    void itShouldNotDeletePatronAndReturn404StatusCode() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/patrons/"+50)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }
}