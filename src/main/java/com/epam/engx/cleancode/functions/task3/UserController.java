package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.Controller;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;

public abstract class UserController implements Controller {

    private UserAuthenticator userAuthenticator;

    public void authenticateUser(String userName, String password) {
        User authenticatedUser = authenticate(userName, password);
        generateLoginResponse(userName, authenticatedUser);
    }

    private User authenticate(String userName, String password) {
        return userAuthenticator.login(userName, password);
    }

    private void generateLoginResponse(String userName, User authenticatedUser) {
        if (authenticatedUser == null) {
            generateFailLoginResponse();
        } else {
            generateSuccessLoginResponse(userName);
        }
    }

    public void setUserAuthenticator(UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }
}
