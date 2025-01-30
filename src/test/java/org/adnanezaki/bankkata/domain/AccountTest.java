package org.adnanezaki.bankkata.domain;
import org.adnanezaki.bankkata.exception.InvalidAmountException;
import org.adnanezaki.bankkata.service.StatementPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class AccountTest {
    @Mock
    private StatementPrinter statementPrinter;

    private Account account;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        account = new Account(statementPrinter);
    }

    @Test
    void shouldDepositMoney() {
        account.deposit(1000, "10-01-2012");
        account.printStatement();
        verify(statementPrinter).print(org.mockito.ArgumentMatchers.anyList());
    }

    @Test
    void shouldThrowExceptionWhenDepositingNegativeAmount() {
        assertThrows(InvalidAmountException.class, () ->
                account.deposit(-1000, "10-01-2012")
        );
    }

    @Test
    void shouldWithdrawMoney() {
        account.deposit(1000, "10-01-2012");
        account.withdraw(500, "11-01-2012");
        account.printStatement();
        verify(statementPrinter).print(org.mockito.ArgumentMatchers.anyList());
    }

    @Test
    void shouldThrowExceptionWhenWithdrawingNegativeAmount() {
        assertThrows(InvalidAmountException.class, () ->
                account.withdraw(-500, "11-01-2012")
        );
    }

    @Test
    void shouldThrowExceptionWhenWithdrawingMoreThanBalance() {
        account.deposit(1000, "10-01-2012");
        assertThrows(InvalidAmountException.class, () ->
                account.withdraw(1500, "11-01-2012")
        );
    }

}
