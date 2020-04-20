package com.gpch.mongo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gpch.mongo.model.Email;

@Repository
public interface EmailRepository extends CrudRepository<Email, String> {
}
