package com.example.tdd.repos;

import com.example.tdd.models.Friend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, Long> {
    Friend findByLastName(String last_name);
    @Query("SELECT f FROM Friend f WHERE MONTH(f.birthday) = MONTH(CURRENT_DATE) AND DAY(f.birthday) >= DAY(CURRENT_DATE)")
    List<Friend> findAllFriendsWithBirthdayThisMonth();

}
