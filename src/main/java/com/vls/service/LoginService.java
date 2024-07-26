package com.vls.service;

import com.vls.util.PasswordHasher;
import com.vls.model.LoginModel;
import com.vls.repository.Login;

public class LoginService {

    private Login login;

    public LoginService(Login login) {
        this.login = login;
    }

    public boolean authenticate(String username, String password) {
        LoginModel user = login.findByUsername(username);
        if (user == null) {
            return false;
        }
        return PasswordHasher.verifyPassword(user.getPassword(), password);
    }

    public void registerUser(String username, String password) {
        if (login.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        String hashedPassword = PasswordHasher.hashPassword(password);
        LoginModel newUser = new LoginModel(username, hashedPassword);
        login.add(newUser);
    }
}
