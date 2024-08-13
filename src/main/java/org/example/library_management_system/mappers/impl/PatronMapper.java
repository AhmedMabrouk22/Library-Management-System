package org.example.library_management_system.mappers.impl;

import org.example.library_management_system.dto.PatronDTO;
import org.example.library_management_system.entity.Patron;
import org.example.library_management_system.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatronMapper implements Mapper<Patron, PatronDTO> {

    private final ModelMapper modelMapper;

    public PatronMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PatronDTO mapTo(Patron patron) {
        return modelMapper.map(patron,PatronDTO.class);
    }

    @Override
    public Patron mapFrom(PatronDTO patronDTO) {
        return modelMapper.map(patronDTO,Patron.class);
    }
}
