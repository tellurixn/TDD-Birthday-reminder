package com.example.tdd.db;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class DBTests {

    private static final String FRIEND_FIRST_NAME = "Ivan";
    private static final String FRIEND_LAST_NAME = "Ivanov";
    private static final Date FRIEND_BIRTHDAY = new Date(2001, Calendar.JANUARY,1);
    @Autowired
    FriendRepository friendRepository;

    @BeforeEach
    public void createFriendForTest(){
        Friend newFriend = new Friend.builder().
                lastName(FRIEND_LAST_NAME).
                firstName(FRIEND_FIRST_NAME).
                birthday(FRIEND_BIRTHDAY).
                build();
        friendRepository.save(friend);
    }

    @AfterEach
    @Transactional
    public void clearTable(){
        friendRepository.deleteAll();
    }

    @Test
    public void findByLastNameTest(){
        Friend foundFriend = friendRepository.findByLastName(FRIEND_LAST_NAME);
        assertNotNull(foundFriend);
    }

}
