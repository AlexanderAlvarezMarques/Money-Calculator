package views;

import java.util.Date;
import models.Currency;

/**
 * @author Alexander Alvarez Marques
 * @version 20-sep-2019
 */
public class ExchangeRate {
    
    private final Date date;
    private final double rate;
    private final Currency from;
    private final Currency to;

    public ExchangeRate(Date date, double rate, Currency from, Currency to) {
        this.date = date;
        this.rate = rate;
        this.from = from;
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" + "date=" + date + ", rate=" + rate + ", from=" + from + ", to=" + to + '}';
    }
}
