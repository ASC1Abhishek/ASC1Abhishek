package com.vls.repository;

import com.vls.model.LoginModel;

public interface Login {
//    boolean authenticate(String username, String password);
    LoginModel findByUsername(String username);
    void add(LoginModel user);
}
