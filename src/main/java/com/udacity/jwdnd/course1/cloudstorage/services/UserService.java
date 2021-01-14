package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UsersMapper userMapper;
    private final HashService hashService;

    public UserService(UsersMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    /*Use this method to check whether a username is available or has already been taken*/
    public boolean isUsernameAvailable(String username) {
        return userMapper.findUser(username) == null;
    }

    /*Use this method to create a new user profile*/
    public int createUser(Users user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new Users(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    /*This method can be used to retrieve a user profile from the database*/
    public Users getUser(String username) {
        /*This accesses the userMapper to get it from the database*/
        return userMapper.findUser(username);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating UserService bean");
    }
}
