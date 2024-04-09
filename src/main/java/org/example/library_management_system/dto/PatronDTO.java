package org.example.library_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatronDTO {
    private Long id;
    private String name;
    private String email;
    private String phone_number;
}
