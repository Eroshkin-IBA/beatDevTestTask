package by.beatdev.beatdevtesttask.services;



import by.beatdev.beatdevtesttask.models.Users;

import java.util.Map;

public interface UserService {
    Map<String, Object> save(Users user);
    Users findById(long id);
    Map<String, String> updateUser(long id);
}
