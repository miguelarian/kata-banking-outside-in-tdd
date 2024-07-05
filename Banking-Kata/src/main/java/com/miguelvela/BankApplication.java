package com.miguelvela;

public class BankApplication {
    public static void main(String[] args) {
        Console console = new Console();
        Clock clock = new Clock();
        StatementPrinter statementPrinter = new StatementPrinter(console);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        Account account = new Account(transactionRepository, statementPrinter);

        account.deposit(1000);
        account.withdraw(500);
        account.deposit(2000);
        account.printStatement();
    }
}
