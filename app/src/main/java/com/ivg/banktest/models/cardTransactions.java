package com.ivg.banktest.models;

public class cardTransactions {


    String dateTransaction;
    String descripctionTranscation;
    String monto;
    String tipo_transaction;


    public cardTransactions(){

    }

    public cardTransactions(String dateTransaction, String descripctionTranscation, String monto, String tipo_transaction) {
        this.dateTransaction = dateTransaction;
        this.descripctionTranscation = descripctionTranscation;
        this.monto = monto;
        this.tipo_transaction = tipo_transaction;
    }


    public String getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getDescripctionTranscation() {
        return descripctionTranscation;
    }

    public void setDescripctionTranscation(String descripctionTranscation) {
        this.descripctionTranscation = descripctionTranscation;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getTipo_transaction() {
        return tipo_transaction;
    }

    public void setTipo_transaction(String tipo_transaction) {
        this.tipo_transaction = tipo_transaction;
    }
}
