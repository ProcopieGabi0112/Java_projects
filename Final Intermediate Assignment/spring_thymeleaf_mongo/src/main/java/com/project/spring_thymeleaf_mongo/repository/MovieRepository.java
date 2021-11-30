package com.project.spring_thymeleaf_mongo.repository;

import com.project.spring_thymeleaf_mongo.model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie, Long> {
}
