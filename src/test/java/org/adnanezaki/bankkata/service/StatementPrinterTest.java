package org.adnanezaki.bankkata.service;
import org.adnanezaki.bankkata.domain.Transaction;
import org.adnanezaki.bankkata.domain.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementPrinterTest {
    private StatementPrinter statementPrinter;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        statementPrinter = new StatementPrinter();
    }

    @Test
    void shouldPrintStatement() {
        var transactions = Arrays.asList(
                new Transaction("10-01-2012", 1000, TransactionType.DEPOSIT, 1000),
                new Transaction("13-01-2012", 2000, TransactionType.DEPOSIT, 3000),
                new Transaction("14-01-2012", 500, TransactionType.WITHDRAWAL, 2500)
        );

        statementPrinter.print(transactions);

        String expectedOutput = String.format("Date || Amount || Balance%n" +
                "10-01-2012 || 1000 || 1000%n" +
                "13-01-2012 || 2000 || 3000%n" +
                "14-01-2012 || 500 || 2500%n");

        assertEquals(expectedOutput, outputStream.toString());
    }
}
