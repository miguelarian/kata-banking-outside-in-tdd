package com.miguelvela;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class TransactionRepository {

    private List<Transaction> transactions;
    private Clock clock;

    public TransactionRepository(Clock clock) {
        this.clock = clock;
        this.transactions = new ArrayList<>();
    }

    public void addDeposit(int amount) {
        Transaction deposit = new Transaction(clock.todayAsString(), amount);
        this.transactions.add(deposit);
    }

    public void addWithdrawal(int amount) {
        Transaction withdrawal = new Transaction(clock.todayAsString(), -amount);
        this.transactions.add(withdrawal);
    }

    public List<Transaction> getAllTransactions() {
        return unmodifiableList(transactions);
    }
}
