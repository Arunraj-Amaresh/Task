package com.example.cx;

public class Record {
    private String accountNumber;
    private String postingAmount;
    private String postingCcy;

    
    
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPostingAmount() {
        return postingAmount;
    }

    public void setPostingAmount(String postingAmount) {
        this.postingAmount = postingAmount;
    }

    public String getPostingCcy() {
        return postingCcy;
    }

    public void setPostingCcy(String postingCcy) {
        this.postingCcy = postingCcy;
    }
}