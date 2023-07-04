package dev.rexdawn.hotelbooking.config;

import dev.rexdawn.hotelbooking.user.User;
import dev.rexdawn.hotelbooking.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    @Autowired
    private UserRepository userRepository;
    @Bean  //This UserDetailsService provides SPRING Authenticity of each User
    public UserDetailsService userDetailsService(){

//        [ ALTERNATIVE WAY OF CALLING THE overridden method 'loadUserByUsername' ]
//        return username -> userRepository.findByUsername(username);

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByUsername(username);
                if(user==null) throw new UsernameNotFoundException("No user exist by this username = "+username);

                return user;
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        // Dao = Data Access Object which is responsible for fetching UserDetails & also encoded password
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // This class is responsible to manage the authentications. Has different methods
        // But one method named 'AuthenticationConfiguration' is used to authenticate a User by username & password
        // The method holds information about AuthenticationManager.
        return config.getAuthenticationManager();
    }
}
