package com.ivg.banktest.models;

public class user {

    String user;
    String lastConnection;

    public user(){

    }

    public user(String user, String lastConnection) {
        this.user = user;
        this.lastConnection = lastConnection;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(String lastConnection) {
        this.lastConnection = lastConnection;
    }
}
