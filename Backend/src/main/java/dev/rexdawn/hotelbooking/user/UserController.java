package dev.rexdawn.hotelbooking.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> allUsers()
    {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }
    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody User user)
    {
        userService.addNewUser(user);
        return ResponseEntity.ok("New user Added Successfully!");
    }
}
