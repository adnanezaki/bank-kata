package org.adnanezaki.bankkata.domain;
import org.adnanezaki.bankkata.exception.InvalidAmountException;
import org.adnanezaki.bankkata.service.StatementPrinter;

import java.util.ArrayList;
import java.util.List;
public class Account {
    private final List<Transaction> transactions;
    private int balance;
    private final StatementPrinter statementPrinter;

    public Account(StatementPrinter statementPrinter) {
        this.transactions = new ArrayList<>();
        this.balance = 0;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount, String date) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
        balance += amount;
        transactions.add(new Transaction(date, amount, TransactionType.DEPOSIT, balance));
    }

    public void withdraw(int amount, String date) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
        if (amount > balance) {
            throw new InvalidAmountException("Insufficient funds");
        }
        balance -= amount;
        transactions.add(new Transaction(date, amount, TransactionType.WITHDRAWAL, balance));
    }

    public void printStatement() {
        statementPrinter.print(transactions);
    }
}
