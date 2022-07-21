package com.ivg.banktest.models;

public class userBalance {

    String generalBalance;
    String userEarnings;
    String userBills;


    public userBalance(){

    }

    public userBalance(String generalBalance, String userEarnings, String userBills) {
        this.generalBalance = generalBalance;
        this.userEarnings = userEarnings;
        this.userBills = userBills;
    }


    public String getGeneralBalance() {
        return generalBalance;
    }

    public void setGeneralBalance(String generalBalance) {
        this.generalBalance = generalBalance;
    }

    public String getUserEarnings() {
        return userEarnings;
    }

    public void setUserEarnings(String userEarnings) {
        this.userEarnings = userEarnings;
    }

    public String getUserBills() {
        return userBills;
    }

    public void setUserBills(String userBills) {
        this.userBills = userBills;
    }
}
