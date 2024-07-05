package com.miguelvela;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {

    public static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";

    private Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        printHeader();
        printStatementLines(transactions);
    }

    private void printHeader() {
        console.printLine(STATEMENT_HEADER);
    }

    private void printStatementLines(List<Transaction> transactions) {
        AtomicInteger balance = new AtomicInteger(0);
        transactions.stream()
                    .map(transaction -> getStatementLine(transaction, balance))
                    .collect(Collectors.toCollection(LinkedList::new))
                    .descendingIterator()
                    .forEachRemaining(console::printLine);
    }

    private String getStatementLine(Transaction transaction, AtomicInteger balance) {
        DecimalFormat df = new DecimalFormat("#.00");
        return transaction.getDate()
                + " | " + df.format(transaction.getAmount())
                + " | " + df.format(balance.addAndGet(transaction.getAmount()));
    }
}
