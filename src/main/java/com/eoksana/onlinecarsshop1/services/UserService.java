package com.eoksana.onlinecarsshop1.services;

import com.eoksana.onlinecarsshop1.entity.Role;
import com.eoksana.onlinecarsshop1.entity.User;
import com.eoksana.onlinecarsshop1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    public List<User> findAllUsers(){
        List<User> users = this.userRepository.findAll();
        return users;
    }

    public Optional<User> createUser(String name, String email, String password) {
        if(userRepository.findByEmail(email).isPresent()) {
            return Optional.empty();
        }
            String md5pass = DigestUtils.md5DigestAsHex(password.getBytes());
            User user = new User(name, email, md5pass);

        user.setRole("USER");
//            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);


        return Optional.of(user);

    }
    public Optional<User> userById(String userId) {
        User user = this.userRepository.findUserByUserId(Long.valueOf(userId));
        if (user != null) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public Optional<User> login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (!userOpt.isPresent()) {
            return Optional.empty();
        }
        User user = userOpt.get();
        String md5pass = DigestUtils.md5DigestAsHex(password.getBytes());

        if (user.getPassword().equals(md5pass)) {
            return Optional.of(user);
        } else

            return Optional.empty();
    }
}
