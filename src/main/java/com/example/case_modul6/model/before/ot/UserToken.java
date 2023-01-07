package com.example.case_modul6.model.before.ot;

import com.example.case_modul6.model.before.Roles;

import java.util.Set;

public class UserToken {
    private long id;
    private String username;
    private String token;
    private Roles roles;

    public UserToken(long id, String username, String token, Roles roles) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.roles = roles;
    }

    public UserToken() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}

