package com.eoksana.onlinecarsshop1.repositories;

import com.eoksana.onlinecarsshop1.entity.Replenishment;
import com.eoksana.onlinecarsshop1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReplenishmentRepository extends JpaRepository<Replenishment, Integer> {
    List<Replenishment> findAllByAccount(User account_user);
}
