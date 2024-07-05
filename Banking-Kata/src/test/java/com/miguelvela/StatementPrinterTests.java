package com.miguelvela;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.*;

class StatementPrinterTests {

    private List<Transaction> NO_TRANSACTIONS = EMPTY_LIST;

    @Mock private Console console;

    private StatementPrinter statementPrinter;

    @BeforeEach
    void setUp() {
        console = mock(Console.class);
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    void should_always_print_the_header() {
        statementPrinter.print(NO_TRANSACTIONS);

        verify(console, times(1)).printLine("DATE | AMOUNT | BALANCE");
    }

    @Test
    void should_print_transactions_in_reverse_chronological_order() {
        List<Transaction> transactions = asList(deposit("10/04/2024", 500),
                                                deposit("11/04/2024", 1500),
                                                withdrawal("12/04/2024", 100));

        statementPrinter.print(transactions);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console, times(1)).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console, times(1)).printLine("12/04/2024 | -100.00 | 1900.00");
        inOrder.verify(console, times(1)).printLine("11/04/2024 | 1500.00 | 2000.00");
        inOrder.verify(console, times(1)).printLine("10/04/2024 | 500.00 | 500.00");
    }

    private Transaction withdrawal(String date, int amount) {
        return new Transaction(date, -amount);
    }

    private Transaction deposit(String date, int amount) {
        return new Transaction(date, amount);
    }
}