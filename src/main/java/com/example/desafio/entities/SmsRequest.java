package com.example.desafio.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

public class SmsRequest implements Serializable {

    @NotEmpty
    private final List<Long> ids;
    @NotBlank
    private final String message;

    public SmsRequest(@JsonProperty("ids") List<Long> ids, @JsonProperty("message") String message) {
        this.ids = ids;
        this.message = message;
    }

    public List<Long> getIds() {
        return ids;
    }

    public String getMessage() {
        return message;
    }

}
