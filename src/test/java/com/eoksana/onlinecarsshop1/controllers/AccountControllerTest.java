package com.eoksana.onlinecarsshop1.controllers;

import com.eoksana.onlinecarsshop1.entity.Account;
import com.eoksana.onlinecarsshop1.entity.User;
import com.eoksana.onlinecarsshop1.services.AccountService;
import com.eoksana.onlinecarsshop1.services.CarService;
import com.eoksana.onlinecarsshop1.services.TransactionService;
import com.eoksana.onlinecarsshop1.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.eoksana.onlinecarsshop1.TestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
class AccountControllerTest {

    private AccountController accountController;

    private AccountService accountService;

    private UserService userService;

    private HttpSession session;

    private CarService carService;

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        accountService = mock(AccountService.class);
        userService = mock(UserService.class);
        session = mock(HttpSession.class);
        carService = mock(CarService.class);
        transactionService = mock(TransactionService.class);
        accountController = new AccountController(accountService, userService, session, carService, transactionService);
    }

    @Test
    public void getAccountServiceTest() throws Exception {
        assertEquals("accountservice", accountController.getAccountService());
    }

    @Test
    public void createAccountSuccessTest() throws Exception {
        // ARRANGE
        Long userTestId = randomLongTestId();
        int accountTestId = randomTestId();
        User user = prepareUser(userTestId);
        Account account = prepareAccount(accountTestId, user);

        when(session.getAttribute("currentUser")).thenReturn(user);
        when(accountService.createAccount(userTestId)).thenReturn(Optional.of(account));

        User userClone = new UserTest(user);
        userClone.setUserId(userTestId);
        userClone.setAccount(account);
        when(userService.updateUser(String.valueOf(userTestId), account)).thenReturn(userClone);

        //ACT
        String url = accountController.createAccount(new BindingAwareModelMap());

        //ASSERT
        assertEquals("redirect:/accountData", url);

        verify(session).getAttribute("currentUser");
        verify(session).setAttribute("currentUser", userClone);
        verify(accountService).createAccount(userTestId);
        verify(userService).updateUser(String.valueOf(userTestId), account);

        verifyNoInteractions(accountService, userService, session, carService, transactionService);
    }

    private Account prepareAccount(int accountId, User testUser) {
        Account account = new Account();
        account.setUser(testUser);
        account.setAccountId(accountId);
        return account;
    }

    private User prepareUser(long userId) {
        User testUser = new User();
        testUser.setEmail(getRandomEmail());
        testUser.setUserId(userId);
        return testUser;
    }

    private class UserTest extends User {

        UserTest(User user) {
            super(user.getUsername(), user.getEmail(), user.getPassword());
        }
    }
}