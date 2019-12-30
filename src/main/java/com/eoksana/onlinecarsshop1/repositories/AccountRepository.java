package com.eoksana.onlinecarsshop1.repositories;

import com.eoksana.onlinecarsshop1.entity.Account;
import com.eoksana.onlinecarsshop1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Optional<Account> findAccountByUser(User user);
}
