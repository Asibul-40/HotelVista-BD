package dev.rexdawn.hotelbooking.auth;

import dev.rexdawn.hotelbooking.config.JwtService;
import dev.rexdawn.hotelbooking.user.User;
import dev.rexdawn.hotelbooking.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Builder
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthResponse register(User request) {
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRoles(List.of("ROLE_USER"));

        userRepository.save(newUser);
        String jwtToken = jwtService.generateToken(newUser);
        return AuthResponse.builder()
                .token(jwtToken)
                .username(newUser.getUsername())
                .build();
    }

    public AuthResponse authenticate(AuthRequest request){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            User user = userRepository.findByUsername(request.getUsername());
            var jwtToken = jwtService.generateToken(user);

            return AuthResponse.builder()
                    .token(jwtToken)
                    .username(request.getUsername())
                    .build();
        }catch (AuthenticationException e){
            return AuthResponse.builder()
                    .token("Invalid Username or password")
                    .username(null)
                    .build();
        }
    }

}
