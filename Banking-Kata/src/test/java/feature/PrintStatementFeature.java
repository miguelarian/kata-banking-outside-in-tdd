package feature;

import com.miguelvela.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class PrintStatementFeature {

    // these are an external dependency so they are mocked
    @Mock private Console console;
    @Mock private Clock clock;

    private TransactionRepository transactionRepository; // Acceptance test is testing the system as a whole
    private StatementPrinter statementPrinter;
    private Account account;

    @BeforeEach
    public void setUp() {
        console = mock(Console.class);
        clock = mock(Clock.class);
        transactionRepository = new TransactionRepository(clock);
        statementPrinter = new StatementPrinter(console);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    public void print_statement_containing_all_transactions() {

        // Given
        given(clock.todayAsString()).willReturn("01/04/2014", "02/04/2014", "10/04/2014");
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        // When
        account.printStatement();

        // Then - in this case, this is a side effect
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console, times(1)).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console, times(1)).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console, times(1)).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console, times(1)).printLine("01/04/2014 | 1000.00 | 1000.00");
    }
}