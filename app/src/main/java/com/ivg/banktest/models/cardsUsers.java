package com.ivg.banktest.models;

public class cardsUsers {

    String numberCard;
    String namerCard;
    String saldo;
    String statusCard;
    String typeUser;


    public cardsUsers(){

    }

    public cardsUsers(String numberCard, String namerCard, String saldo, String statusCard, String typeUser) {
        this.numberCard = numberCard;
        this.namerCard = namerCard;
        this.saldo = saldo;
        this.statusCard = statusCard;
        this.typeUser = typeUser;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getNamerCard() {
        return namerCard;
    }

    public void setNamerCard(String namerCard) {
        this.namerCard = namerCard;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getStatusCard() {
        return statusCard;
    }

    public void setStatusCard(String statusCard) {
        this.statusCard = statusCard;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
}
