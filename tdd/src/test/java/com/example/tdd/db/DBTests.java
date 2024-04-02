package com.example.tdd.db;

import com.example.tdd.TddApplication;
import com.example.tdd.models.Friend;
import com.example.tdd.repos.FriendRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class DBTests {

    private static final String FRIEND_FIRST_NAME = "Ivan";
    private static final String FRIEND_LAST_NAME = "Ivanov";
    private static final Date FRIEND_BIRTHDAY = new Date(2001, Calendar.JANUARY,1);
    private static final Friend FRIEND =  Friend.builder().
            lastName(FRIEND_LAST_NAME).
            firstName(FRIEND_FIRST_NAME).
            birthday(FRIEND_BIRTHDAY).
            build();
    @Autowired
    FriendRepository friendRepository;

    @BeforeEach
    public void createFriendForTest(){
        friendRepository.save(FRIEND);
    }

    @AfterEach
    @Transactional
    public void clearTable(){
        friendRepository.delete(FRIEND);
    }

    @Test
    public void findByLastNameTest(){
        Friend foundFriend = friendRepository.findByLastName(FRIEND_LAST_NAME);
        assertNotNull(foundFriend);
    }

    @Test
    public void saveTest(){
        Friend newFriend = Friend.builder().
                lastName("Popov").
                firstName("Pavel").
                birthday(new Date(2005, Calendar.JUNE, 5)).
                build();
        friendRepository.save(newFriend);
        Friend foundFriend = friendRepository.findByLastName("Popov");
        assertEquals(foundFriend, newFriend);
    }

    @Test
    public void updateTest(){
        String last_name = "Sergeev";
        FRIEND.setLastName(last_name);
        friendRepository.update(FRIEND);
        assertEquals(FRIEND.getLastName(), last_name);
    }

}
