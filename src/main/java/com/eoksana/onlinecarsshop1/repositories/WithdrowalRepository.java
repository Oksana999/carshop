package com.eoksana.onlinecarsshop1.repositories;

import com.eoksana.onlinecarsshop1.entity.User;
import com.eoksana.onlinecarsshop1.entity.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WithdrowalRepository extends JpaRepository<Withdrawal, Integer> {
    List<Withdrawal> findAllByAccount(User user_account);
}
