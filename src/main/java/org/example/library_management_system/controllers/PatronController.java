package org.example.library_management_system.controllers;

import jakarta.validation.Valid;
import org.example.library_management_system.dto.BookDTO;
import org.example.library_management_system.dto.PatronDTO;
import org.example.library_management_system.handler.ResponseHandler;
import org.example.library_management_system.services.BookService;
import org.example.library_management_system.services.PatronService;
import org.example.library_management_system.utils.HttpStatusMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/patrons")
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }


    @PostMapping(path = "")
    public ResponseEntity<ResponseHandler> create(@RequestBody @Valid PatronDTO patronDTO) {
        PatronDTO newPatron = patronService.create(patronDTO);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS, HttpStatus.CREATED,"Patron Created Successfully",newPatron );
    }

    @GetMapping(path = "")
    public  ResponseEntity<ResponseHandler> getAll(@RequestParam(name = "page",defaultValue = "0") Integer page, @RequestParam(name = "limit",defaultValue = "15") Integer limit) {
        Pageable pageable = PageRequest.of(page,limit);
        Page<PatronDTO> patrons = patronService.findAll(pageable);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS,HttpStatus.OK,"Patrons Get Successfully",patrons.getContent());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseHandler> delete(@PathVariable Long id) {
        patronService.delete(id);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS,HttpStatus.OK,"Patron Deleted Successfully", null);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseHandler> getById(@PathVariable Long id) {
        Optional<PatronDTO> patron = patronService.findById(id);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS,HttpStatus.OK,"Patron Get Successfully", patron);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseHandler> update(@PathVariable Long id, @RequestBody @Valid PatronDTO patronDTO) {
        PatronDTO patron = patronService.updateById(id,patronDTO);
        return ResponseHandler.generateResponse(HttpStatusMessage.SUCCESS,HttpStatus.OK,"Patron Updated successfully",patron);
    }
}
