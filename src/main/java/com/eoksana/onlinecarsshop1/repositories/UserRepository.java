package com.eoksana.onlinecarsshop1.repositories;

import com.eoksana.onlinecarsshop1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//   User findUserByUsername(String username);
   Optional<User> findByEmail(String email);
//   User findUserByUserId(Long userId);
   User findUserByUserId(Long userId);



}
