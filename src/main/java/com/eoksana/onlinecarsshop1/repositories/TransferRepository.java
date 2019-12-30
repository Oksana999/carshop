package com.eoksana.onlinecarsshop1.repositories;

import com.eoksana.onlinecarsshop1.entity.Transfer;
import com.eoksana.onlinecarsshop1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    List<Transfer> findAllByAccountFrom(User account_user);
    List<Transfer> findAllByAccountTo(User account_user);
}
