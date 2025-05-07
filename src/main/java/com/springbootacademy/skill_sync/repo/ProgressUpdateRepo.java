package com.springbootacademy.skill_sync.repo;

import com.springbootacademy.skill_sync.entity.ProgressUpdate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProgressUpdateRepo extends MongoRepository<ProgressUpdate, String> {
}
