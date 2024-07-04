package com.miguelvela;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryTests {

    private static final String TODAY = "10/01/2024";
    private TransactionRepository repository;
    @Mock
    private Clock clock;

    @BeforeEach
    void setUp() {
        clock = mock(Clock.class);
        repository = new TransactionRepository(clock);
        when(clock.todayAsString()).thenReturn(TODAY);
    }

    @Test
    void addDeposit_should_add_a_new_transaction() { // this should be an integration test
        repository.addDeposit(1000);

        List<Transaction> transactions = repository.getAllTransactions();
        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0), is(transaction(TODAY, 1000)));
    }

    @Test
    void addWithdrawal_should_add_a_new_transaction() { // this should be an integration test
        repository.addWithdrawal(100);

        List<Transaction> transactions = repository.getAllTransactions();
        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0), is(transaction(TODAY, -100)));
    }

    private Transaction transaction(String date, int amount) {
        return new Transaction(date, amount);
    }
}