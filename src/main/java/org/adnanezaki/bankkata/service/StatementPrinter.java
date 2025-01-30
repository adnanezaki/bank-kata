package org.adnanezaki.bankkata.service;

import org.adnanezaki.bankkata.domain.Transaction;

import java.util.List;

public class StatementPrinter {
    private static final String HEADER = "Date || Amount || Balance";
    private static final String LINE_FORMAT = "%s || %d || %d";

    public void print(List<Transaction> transactions) {
        StringBuilder output = new StringBuilder(HEADER);

        for (Transaction transaction : transactions) {
            output.append(System.lineSeparator())
                    .append(String.format(LINE_FORMAT,
                            transaction.getDate(),
                            transaction.getAmount(),
                            transaction.getBalance()));
        }

        System.out.print(output.toString() + System.lineSeparator());
    }
}
