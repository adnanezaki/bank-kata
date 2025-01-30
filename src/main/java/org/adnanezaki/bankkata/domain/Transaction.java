package org.adnanezaki.bankkata.domain;

public class Transaction {
    private final String date;
    private final int amount;
    private final TransactionType type;
    private final int balance;

    public Transaction(String date, int amount, TransactionType type, int balance) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public int getBalance() {
        return balance;
    }
}
