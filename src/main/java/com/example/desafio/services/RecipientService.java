package com.example.desafio.services;

import com.example.desafio.entities.Recipient;
import com.example.desafio.repositories.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipientService {

    private final RecipientRepository recipientRepository;

    @Autowired
    public RecipientService(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    public List<Recipient> getAllRecipients() {
        return recipientRepository.findAll();
    }

    public Recipient getRecipient(Long id) {
        Optional<Recipient> recipient = recipientRepository.findById(id);
        return recipient.orElse(null);
    }
    public Recipient saveRecipient(Recipient recipient) {
        if (recipient == null)
            throw new IllegalStateException("Invalid recipient.");
        return recipientRepository.save(recipient);
    }

    public Recipient updateRecipient(Long id, Recipient recipient) {
        if (recipientExists(recipient.getId())) {
            recipient.setId(id);
            return recipientRepository.save(recipient);
        } else {
            throw new IllegalStateException("Recipient does not exist.");
        }
    }
    public boolean deleteRecipient(Long id) {
        if (!recipientExists(id))
            throw new IllegalStateException("Recipient does not exist.");

        try {
            recipientRepository.deleteById(id);
            return true;
        } catch (Exception ignored) {

        }
        return false;
    }

    private boolean recipientExists(Long id) {
        return getRecipient(id) != null;
    }
}
