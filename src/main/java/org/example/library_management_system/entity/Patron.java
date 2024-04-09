package org.example.library_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "patrons")
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "patron_id_seq")
    private Long id;
    @NotNull(message = "name is require")
    @NotBlank(message = "name must not empty")
    private String name;
    @NotNull(message = "email is require")
    @Email(message = "invalid email")
    @Column(unique = true)
    private String email;
    @NotNull(message = "phone number is require")
    @NotBlank(message = "phone number must not empty")
    @Pattern(regexp = "^[+](?:[0-9]?){6,15}[0-9]$" , message = "Invalid phone number")
    private String phone_number;
}
