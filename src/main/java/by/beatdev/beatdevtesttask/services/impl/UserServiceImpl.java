package by.beatdev.beatdevtesttask.services.impl;

import by.beatdev.beatdevtesttask.exeption.UserExistingEmailException;

import by.beatdev.beatdevtesttask.exeption.UserNotFoundException;
import by.beatdev.beatdevtesttask.models.Users;
import by.beatdev.beatdevtesttask.repositories.UserRepo;
import by.beatdev.beatdevtesttask.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepo userRepo;
    Map<String, String> updateResponse;

    @Override
    public Map<String, Object> save(Users user) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("id", userRepo.save(user).getId());
        }catch(Exception e){
            System.out.println(e);
            throw new UserExistingEmailException("User with this email address already exists");
        }
        return map;
    }

    @Override
    public Users findById(long id) {
        if (!userRepo.findById(id).isPresent())
            throw new UserNotFoundException("User does not exist");
        return userRepo.findById(id).get();
    }

    @Override
    public Map<String, String> updateUser(long id) {
        if (!userRepo.findById(id).isPresent())
            throw new UserNotFoundException("User does not exist");
        Users user = userRepo.findById(id).get();
        updateResponse.put("id", user.getId().toString());
        updateResponse.put("previous status", user.getStatus());
        //изменяем статус
        if (user.getStatus().equalsIgnoreCase("offline")) {
            user.setStatus("online");
            updateResponse.put("current status", user.getStatus());
        } else {
            user.setStatus("offline");
            updateResponse.put("current status", user.getStatus());
        }
        userRepo.save(user);

        return updateResponse;
    }


}


