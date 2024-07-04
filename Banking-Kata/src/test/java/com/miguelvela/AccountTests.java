package com.miguelvela;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class AccountTests {

    @Mock private TransactionRepository transactionRepository;
    @Mock private StatementPrinter statementPrinter;
    private Account account;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        statementPrinter = mock(StatementPrinter.class);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    void should_store_deposit() {
        account.deposit(1000);

        verify(transactionRepository, times(1)).addDeposit(1000);
    }

    @Test
    void should_store_withdrawal() {
        account.withdraw(100);

        verify(transactionRepository, times(1)).addWithdrawal(100);
    }

    @Test
    void should_print_statement() {
        List<Transaction> transactions = Arrays.asList(new Transaction("10/01/2024", 1000));
        when(transactionRepository.getAllTransactions()).thenReturn(transactions);

        account.printStatement();

        verify(transactionRepository, times(1)).getAllTransactions();
        verify(statementPrinter, times(1)).print(transactions);
    }
}