package org.example.library_management_system.services;

import org.example.library_management_system.dto.PatronDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PatronService {
    PatronDTO create(PatronDTO patronDTO);

    void delete(Long id);
    Optional<PatronDTO> findById(Long id);
    List<PatronDTO> findAll();

    Page<PatronDTO> findAll(Pageable pageable);
    PatronDTO updateById(Long id, PatronDTO patronDTO);

    boolean isExist(Long id);
}
