package com.gpch.mongo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gpch.mongo.model.Phone;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, String> {
}
