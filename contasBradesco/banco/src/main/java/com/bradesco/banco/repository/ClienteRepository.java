package com.bradesco.banco.repository;

import com.bradesco.banco.dto.Clientes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Clientes, String> {
}