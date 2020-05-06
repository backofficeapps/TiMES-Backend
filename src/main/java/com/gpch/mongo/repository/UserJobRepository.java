package com.gpch.mongo.repository;

import org.springframework.data.repository.CrudRepository;

import com.gpch.mongo.model.UserJob;

public interface UserJobRepository extends CrudRepository<UserJob, String>{
}
