package com.gpch.mongo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.gpch.mongo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
