package com.scaler.bookmyshowmorning.services;

import com.scaler.bookmyshowmorning.models.User;
import com.scaler.bookmyshowmorning.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.*;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User signup(String name, String email, String password){
        // First we check if user with email already exists
        // Fetch user with userId from userRepository
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new IllegalArgumentException("User already exists.Please try with a different email");
        }
        // If exists, we throw exception
        User user = new User();
//        user.setId(1L); // UUID Generator

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        // else , we insert the user data in user table using repository
        // return the user
        return userRepository.save(user);
    }
}

// Password -> encodedPass -X> Password
// During login - match(password, encodedPass)
//        if (bCryptPasswordEncoder.matches("password", user.getPassword())){
//            System.out.println("Password matches!");
//        }