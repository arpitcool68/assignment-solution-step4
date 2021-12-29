package com.stackroute.newz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.newz.model.NewsSource;

public interface NewsSourceRepository extends MongoRepository<NewsSource, Integer> {

}
