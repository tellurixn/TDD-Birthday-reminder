package com.example.tdd.repos;

import com.example.tdd.models.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<Friend, Long> {
    Friend findByLastName(String last_name);
}
