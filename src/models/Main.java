package models;

import views.RestExchangeRateLoader;
import views.CsvCurrencyLoader;
import views.ExchangeRate;
import java.util.List;

/**
 * @author Alexander Alvarez Marques
 * @version 20-sep-2019
 */
public class Main {

    public static void main(String[] args){
        
        CsvCurrencyLoader currencyLoader = new CsvCurrencyLoader("currencies.csv");
        List<Currency> currencies = currencyLoader.load();
        
        RestExchangeRateLoader exchangesLoader = new RestExchangeRateLoader(currencies);
        List<ExchangeRate> exchanges = exchangesLoader.load();
        
        System.out.println(exchanges.get(1).toString());
    }
}
