package com.eoksana.onlinecarsshop1.controllers;

import com.eoksana.onlinecarsshop1.entity.Account;
import com.eoksana.onlinecarsshop1.entity.User;
import com.eoksana.onlinecarsshop1.repositories.UserRepository;
import com.eoksana.onlinecarsshop1.services.AccountService;
import com.eoksana.onlinecarsshop1.services.CarService;
import com.eoksana.onlinecarsshop1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private final AccountService accountService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpSession session;
    private final CarService carService;
    private final CarController carController;


    @GetMapping(value = "/accountservice")
    public String getAccountService(){
        return "accountservice";
    }

    @PostMapping(value = "/createAccount/{userId}")
    public String createAccount( Model model,
                                 @RequestParam String userId) {
Optional<Account> account = accountService.createAccount(userId);
    User user = this.userService.userById(userId).get();
        if(account.isPresent()){
        model.addAttribute("account", account.get());
        model.addAttribute("user", user);

        return "accountservice";

//    @PostMapping(value = "/createAccount")
//            public String createAccount(HttpSession session, Model model){
//        User currentUser = (User) session.getAttribute("currentUser");
//        Long userId = currentUser.getUserId();
//        Optional<Account> account = accountService.createAccount(userId);
//        User user = this.userService.userById(String.valueOf(userId)).get();
//        if(account.isPresent()){
//        model.addAttribute("account", account.get());
//        model.addAttribute("user", user);
//
//            return "accountservice";

        } else {

            model.addAttribute("message", "Account already exists or you did not login ");

            return "error";

       }
    }

    @PostMapping(value = "/addMoneyToAccount/{accountId}")
    public String replenishment(Model model,
                                 @RequestParam String accountId,
                                 @RequestParam String amount){
       Account account = this.accountService.replenishment(accountId, amount).get();
       model.addAttribute("account", account);
        return "accountservice";
    }
    @GetMapping(value = "/checkAccount")
    public String checkAccount(Model model,
                               @RequestParam String accountId){
        Account account = this.accountService.findById(accountId);
        model.addAttribute("account", account);
        return "accountservice";
    }
    @PostMapping(value = "/account/{accountId}")
    public String getAccount (Model model,
                              @RequestParam String accountId){
        Account account = this.accountService.findById(accountId);
        model.addAttribute("account", account);
        return "accountservice.html";
    }
    @GetMapping(value = "/buycar/{carId}")
    public String buy (){
        return "buycar";
    }

    @PostMapping(value = "/buycar/{carId}")
    public String buycar(Model model,
                         @RequestParam String userId,
                         @RequestParam String carId) {
        Optional<Account> account = this.accountService.buyCar(userId, carId);
        this.carService.sell(carId);

        if (account.isPresent()) {
            model.addAttribute("account", account.get());
            return "accountservice.html";

        } else {

            model.addAttribute("message", "It is not enough money on your account. You can't buy this car ");

            return "error";

        }
    }
}
