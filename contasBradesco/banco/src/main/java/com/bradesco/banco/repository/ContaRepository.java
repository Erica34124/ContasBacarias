package com.bradesco.banco.repository;

import com.bradesco.banco.domain.Conta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends MongoRepository<Conta, String> {
    List<Conta> findByClienteId(String id);
}