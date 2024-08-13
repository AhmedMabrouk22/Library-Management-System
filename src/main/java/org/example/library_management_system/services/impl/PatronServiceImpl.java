package org.example.library_management_system.services.impl;

import org.example.library_management_system.dto.BookDTO;
import org.example.library_management_system.dto.PatronDTO;
import org.example.library_management_system.entity.Book;
import org.example.library_management_system.entity.Patron;
import org.example.library_management_system.exceptions.NotFoundException;
import org.example.library_management_system.mappers.Mapper;
import org.example.library_management_system.repositories.BookRepo;
import org.example.library_management_system.repositories.PatronRepo;
import org.example.library_management_system.services.PatronService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatronServiceImpl implements PatronService {


    private final PatronRepo patronRepo;
    private final Mapper<Patron, PatronDTO> patronMapper;

    public PatronServiceImpl(PatronRepo patronRepo,Mapper<Patron, PatronDTO> patronMapper) {
        this.patronRepo = patronRepo;
        this.patronMapper = patronMapper;
    }

    @Override
    public PatronDTO create(PatronDTO patronDTO) {
        Patron patron = patronMapper.mapFrom(patronDTO);
        Patron res = patronRepo.save(patron);
        return patronMapper.mapTo(res);
    }

    @Override
    public void delete(Long id) {
        if (!isExist(id))
            throw new NotFoundException("Patron with id " + id + " not found");
        patronRepo.deleteById(id);
    }

    @Override
    public Optional<PatronDTO> findById(Long id) {
        Optional<Patron> patron = patronRepo.findById(id);
        if (patron.isEmpty())
            throw new NotFoundException("patron with id " + id + " not found");
        return patron.map(patronMapper::mapTo);
    }

    @Override
    public List<PatronDTO> findAll() {
        List<Patron> patrons = new ArrayList<>(patronRepo.findAll());
        return patrons.stream().map(patronMapper::mapTo).collect(Collectors.toList());
    }

    @Override
    public Page<PatronDTO> findAll(Pageable pageable) {
        Page<Patron> patrons = patronRepo.findAll(pageable);
        return patrons.map(patronMapper::mapTo);
    }

    @Override
    public PatronDTO updateById(Long id, PatronDTO patronDTO) {
        if (!isExist(id))
            throw new NotFoundException("patron with id " + id + " not found");
        Patron patron = patronMapper.mapFrom(patronDTO);
        patron.setId(id);
        Patron res =  patronRepo.save(patron);
        return patronMapper.mapTo(res);
    }

    @Override
    public boolean isExist(Long id) {
        return patronRepo.existsById(id);
    }
}
