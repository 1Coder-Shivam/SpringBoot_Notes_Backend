package com.nagarro.notes.entity;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String userName;
    private final int userId;


    public JwtResponse(String jwttoken, String userName, int id) {
        this.jwttoken = jwttoken;
        this.userName = userName;
        this.userId= id;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getUserName() {
        return this.userName;
    }

    public int getUserId() {
        return this.userId;
    }
}