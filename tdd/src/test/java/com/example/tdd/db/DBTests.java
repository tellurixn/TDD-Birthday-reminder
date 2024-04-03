package com.example.tdd.db;

import com.example.tdd.TddApplication;
import com.example.tdd.models.Friend;
import com.example.tdd.repos.FriendRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ContextConfiguration(classes = TddApplication.class)
@ActiveProfiles("test")
public class DBTests {

    private static final String FRIEND_FIRST_NAME = "Ivan";
    private static final String FRIEND_LAST_NAME = "Ivanov";
    private static final int FRIEND_BIRTHDAY_YEAR = 2001;
    private static final int FRIEND_BIRTHDAY_MONTH = Calendar.FEBRUARY;
    private static final int FRIEND_BIRTHDAY_DAY = 15;
    private static final LocalDate FRIEND_BIRTHDAY = LocalDate.of(
            FRIEND_BIRTHDAY_YEAR,
            FRIEND_BIRTHDAY_MONTH,
            FRIEND_BIRTHDAY_DAY);
    private static final int FRIEND_AGE = Period.between(FRIEND_BIRTHDAY, LocalDate.now()).getYears();
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
                birthday(LocalDate.of(2005, Calendar.JUNE, 5)).
                build();
        friendRepository.save(newFriend);
        Friend foundFriend = friendRepository.findByLastName("Popov");
        assertEquals(foundFriend, newFriend);
    }

    @Test
    public void updateTest(){
        String first_name = "Sergey";
        FRIEND.setFirstName(first_name);
        friendRepository.save(FRIEND);
        assertEquals(FRIEND.getFirstName(), first_name);
    }

    @Test
    public void getAgeTest(){
        Friend foundFriend = friendRepository.findByLastName(FRIEND_LAST_NAME);
        assertEquals(FRIEND_AGE, foundFriend.getAge());
    }

    @Test
    public void findAllFriendsWithBirthdayThisMonthTest(){
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();

        FRIEND.setBirthday(LocalDate.of(FRIEND_BIRTHDAY_YEAR, currentMonth, FRIEND_BIRTHDAY_DAY));
        friendRepository.save(FRIEND);

        List<Friend> friendsThisMonth = friendRepository.findAllFriendsWithBirthdayThisMonth();
        for(Friend friend : friendsThisMonth) {
            LocalDate birthday = friend.getBirthday();
            int birthdayMonth = birthday.getMonthValue();

            Assertions.assertThat(birthdayMonth).isEqualTo(currentMonth);
        }
    }

}
