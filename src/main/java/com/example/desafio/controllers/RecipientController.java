package com.example.desafio.controllers;

import com.example.desafio.activeMQ.ActiveMqProducer;
import com.example.desafio.entities.Recipient;
import com.example.desafio.entities.SmsRequest;
import com.example.desafio.services.RecipientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RecipientController {

    private final RecipientService recipientService;
    private final ActiveMqProducer activeMqProducer;

    @Autowired
    public RecipientController(RecipientService recipientService, ActiveMqProducer activeMqProducer) {
        this.recipientService = recipientService;
        this.activeMqProducer = activeMqProducer;
    }

    @GetMapping("/recipient/all")
    public List<Recipient> getAllRecipients() {
        return recipientService.getAllRecipients();
    }

    @GetMapping("/recipient/{id}")
    public Recipient getRecipientById(@PathVariable Long id) {
        return recipientService.getRecipient(id);
    }

    @PostMapping("/recipient")
    public Recipient createRecipient(@Valid @RequestBody Recipient recipient) {
        return recipientService.saveRecipient(recipient);
    }

    @PostMapping("/recipient/send")
    public ResponseEntity<String> sendRecipient(@Valid @RequestBody SmsRequest smsRequest) {
        try {
            activeMqProducer.sendToQueue(smsRequest);
            return new ResponseEntity<>("Sent", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/recipient/{id}")
    public Recipient updateRecipient(@PathVariable Long id, @Valid @RequestBody Recipient recipient) {
        return recipientService.updateRecipient(id, recipient);
    }

    @DeleteMapping("/recipient/{id}")
    public boolean deleteRecipient(@PathVariable long id) {
        return recipientService.deleteRecipient(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
