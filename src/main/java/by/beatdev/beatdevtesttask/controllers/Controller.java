package by.beatdev.beatdevtesttask.controllers;

import by.beatdev.beatdevtesttask.models.Users;
import by.beatdev.beatdevtesttask.response.ResponseHandler;

import by.beatdev.beatdevtesttask.services.UserService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class Controller {
    UserService userService;

    private static void run() {
        System.out.println("задержка");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable String userId) {

        //"заглушка" с имитацией обращения и задержкой по времени 5-10 сек."
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ResponseHandler.responseBuilder("User details", HttpStatus.OK, userService.findById(Integer.parseInt(userId)));
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody Users user) {

        //"заглушка" с имитацией обращения и задержкой по времени 5 сек.
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ResponseHandler.responseBuilder("Saved user id", HttpStatus.OK, userService.save(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUserStatus(@PathVariable String userId) {

        //"заглушка" с имитацией обращения и задержкой по времени 5-10 сек."
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ResponseHandler.responseBuilder("Updated user", HttpStatus.OK, userService.updateUser(Integer.parseInt(userId)));
    }
}
