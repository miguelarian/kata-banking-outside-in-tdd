package com.miguelvela;

public class Account {

    private final StatementPrinter statementPrinter;
    private TransactionRepository transactionRepository;

    public Account(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        this.transactionRepository.addDeposit(amount);
    }

    public void withdraw(int amount) {
        this.transactionRepository.addWithdrawal(amount);
    }

    public void printStatement() {
        statementPrinter.print(transactionRepository.getAllTransactions());
    }
}
