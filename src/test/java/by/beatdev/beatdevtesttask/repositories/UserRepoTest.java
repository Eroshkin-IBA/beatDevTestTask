package by.beatdev.beatdevtesttask.repositories;


import by.beatdev.beatdevtesttask.models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepoTest {

    @Autowired
    UserRepo userRepo;

    Users user;



    @BeforeEach
    void setUp() {
        user = new Users(1L, "Artem","Eroshkin", "@gmail.com", "url", "offline");
        userRepo.save(user);
    }

    @AfterEach
    void tearDown() {
    }
    // Test case SUCCESS
    @Test
    void findById() {
        Users userFromDB = userRepo.findById(1L).get();
        assertEquals(userFromDB.getId(), user.getId());

    }
    // Test case FAILURE
    @Test
    void findById_notFound() {

        assertTrue(!userRepo.findById(15l).isPresent());

    }
}