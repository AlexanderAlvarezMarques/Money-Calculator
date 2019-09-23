package models;

/**
 * @author Alexander Alvarez Marques
 * @version 20-sep-2019
 */
public class Money {
    
    private final double amount;
    private final Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
