package com.eoksana.onlinecarsshop1.services;

import com.eoksana.onlinecarsshop1.entity.*;
import com.eoksana.onlinecarsshop1.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;
    private final UserService userService;
    private final HttpSession session;
    private final ReplenishmentRepository replenishmentRepository;
    private final WithdrowalRepository withdrowalRepository;
    private final CarRepository carRepository;
    private final CarService carService;

    public Optional <Account> createAccount(String userId) {
        Optional<User> userOpt = userService.userById(userId);
        if(!userOpt.isPresent()){
            return Optional.empty();
        }else if(userOpt.get().getAccount() == null){
            User user = userOpt.get();
            Account account = new Account();
            account.setUser(user);
            this.accountRepository.save(account);
            return Optional.of(account);
        } else {
            Account account = userOpt.get().getAccount();
            return Optional.of(userOpt.get().getAccount());
//            return Optional.empty();


        }
    }
    public Account findById(String accountId){
        Account account = this.accountRepository.findById(Integer.valueOf(accountId)).orElseThrow(() -> new RuntimeException("Account was not found"));
        return account;
    }

    public Optional<Account> replenishment(String accountId, String amount) {
        Optional<Account> accountOpt = this.accountRepository.findById(Integer.valueOf(accountId));
        if (!accountOpt.isPresent()) {
            return Optional.empty();
        }
            Account account = accountOpt.get();
        double accountAmount = account.getAmount();
        Double addAmount = Double.valueOf(amount);
            account.setAmount(accountAmount + addAmount);
            this.accountRepository.save(account);
            Replenishment replenishment = new Replenishment();
            replenishment.setAccount(account);
            replenishment.setDateTime(LocalDateTime.now());
            replenishment.setValue(addAmount);
            this.replenishmentRepository.save(replenishment);
            return accountOpt;
        }

        public Optional<Account> buyCar(String userId,  String carId){
            Optional<User> userOpt = this.userService.userById(userId);
            if(!userOpt.isPresent()){
                return Optional.empty();
            }
            User user = userOpt.get();
            Optional<Account> accountByUserOpt = this.accountRepository.findAccountByUser(user);

            if (!accountByUserOpt.isPresent()) {
                return Optional.empty();
            }
            Optional<Car> carById = this.carRepository.findById(Long.parseLong(carId));
            if(!carById.isPresent()){
                return Optional.empty();
            }
            Car car = carById.get();
            Account account = accountByUserOpt.get();
            double accountAmount = account.getAmount();
            double withdrawAmount = car.getPrice();

            if(accountAmount < withdrawAmount){
                return Optional.empty();
            }
            account.setAmount(accountAmount - withdrawAmount);
            this.accountRepository.save(account);

//            car.setInStock(false);
//            carRepository.save(car);

            Withdrawal withdrawal = new Withdrawal();
            withdrawal.setAccount(account);
            withdrawal.setDateTime(LocalDateTime.now());
            withdrawal.setValue(withdrawAmount);
            this.withdrowalRepository.save(withdrawal);


            return Optional.of(account);
        }
}


