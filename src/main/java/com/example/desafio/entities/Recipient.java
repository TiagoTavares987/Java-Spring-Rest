package com.example.desafio.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table
public class Recipient {

    @Id
    @SequenceGenerator(
            name = "recipient_sequence",
            sequenceName = "recipient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipient_sequence"
    )


    private Long id;

    @Valid
    @NotBlank(message = "Name is mandatory.")
    private String name;

    @NotBlank(message = "Phone number is mandatory.")
    @Pattern(regexp = "^\\+351\\d{9}$", message = "Invalid phone number.")
    private String phoneNumber;

    public Recipient() { }

    public Recipient(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}