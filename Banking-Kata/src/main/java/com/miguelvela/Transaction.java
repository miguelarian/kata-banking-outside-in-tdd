package com.miguelvela;

import java.util.Objects;

public class Transaction {

    private final int amount;
    private final String date;

    public Transaction(String date, int amount) {
        this.amount = amount;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amount == that.amount && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date);
    }
}
