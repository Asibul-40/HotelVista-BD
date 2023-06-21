package dev.rexdawn.hotelbooking.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
    public void addNewUser(User user)
    {
        userRepository.save(user);
    }
}
