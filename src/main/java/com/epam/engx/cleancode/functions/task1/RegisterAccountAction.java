package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.Account;
import com.epam.engx.cleancode.functions.task1.thirdpartyjar.AccountManager;
import com.epam.engx.cleancode.functions.task1.thirdpartyjar.Address;
import com.epam.engx.cleancode.functions.task1.thirdpartyjar.PasswordChecker;
import com.epam.engx.cleancode.functions.task1.thirdpartyjar.TooShortPasswordException;
import com.epam.engx.cleancode.functions.task1.thirdpartyjar.WrongAccountNameException;
import com.epam.engx.cleancode.functions.task1.thirdpartyjar.WrongPasswordException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {

    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        validateAccountName(account);
        validatePassword(account.getPassword());
        prepareAccount(account);
        accountManager.createNewAccount(account);
    }

    private void validateAccountName(Account account) {
        if (account.getName().length() <= 5) {
            throw new WrongAccountNameException();
        }
    }

    private void validatePassword(String password) {
        validatePasswordLength(password);
        validatePasswordFormat(password);
    }

    private void validatePasswordLength(String password) {
        if (password.length() <= 8) {
            throw new TooShortPasswordException();
        }
    }

    private void validatePasswordFormat(String password) {
        if (passwordChecker.validate(password) != OK) {
            throw new WrongPasswordException();
        }
    }

    private void prepareAccount(Account account) {
        account.setCreatedDate(new Date());
        account.setAddresses(collectAddresses(account));
    }

    private List<Address> collectAddresses(Account account) {
        List<Address> addresses = new ArrayList<>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        return addresses;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }
}