package dev.rexdawn.hotelbooking.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> allUsers()
    {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> GetUserByUsername(@PathVariable String username){
       return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user)
    {
        userService.addNewUser(user);
        return ResponseEntity.ok("New user Added Successfully!");
    }
}
