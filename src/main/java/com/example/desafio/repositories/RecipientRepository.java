package com.example.desafio.repositories;

import com.example.desafio.entities.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {
}
