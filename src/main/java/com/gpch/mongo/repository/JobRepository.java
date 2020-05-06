package com.gpch.mongo.repository;

import org.springframework.data.repository.CrudRepository;
import com.gpch.mongo.model.Job;

public interface JobRepository extends CrudRepository<Job, String> {
}
