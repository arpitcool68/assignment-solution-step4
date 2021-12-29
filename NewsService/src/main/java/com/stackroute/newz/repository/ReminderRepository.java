package com.stackroute.newz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.newz.model.Reminder;

@Repository
public interface ReminderRepository extends MongoRepository<Reminder, String> {

}
