package views;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Currency;

/**
 * @author Alexander Alvarez Marques
 * @version 20-sep-2019
 */
public class RestExchangeRateLoader {

    private final List<Currency> currencies;

    public RestExchangeRateLoader(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<ExchangeRate> load() {
        List<ExchangeRate> list = new ArrayList<>();
        
        for (Currency currency : currencies)
            list.addAll(loadExchangeRatesOfCurrency(currency));
        
        return list;
    }

    private List<ExchangeRate> loadExchangeRatesOfCurrency(Currency source) {
        List<ExchangeRate> list = new ArrayList<>();
        JsonData data = loadJsonData(source);
        
        for (String code : data.rates.keySet()) {
            Currency target = findCurrency(code);
            list.add(new ExchangeRate(data.date, data.rates.get(code), source, target));
        }
        
        return list;
    }


    private JsonData loadJsonData(Currency currency) {
        try {
            return loadJsonData(new URL("https://api.exchangeratesapi.io/latest?base=" + currency.getCode()));
        } catch (IOException e) {
            System.out.println("Error en la lectura del fichero JSON");
            System.out.println("Error del sistema:\n" + e.getMessage());
            return null;
        }
    }

    private JsonData loadJsonData(URL url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return new Gson().fromJson(reader.readLine(), JsonData.class);
        }
    }

    private Currency findCurrency(String code) {
        
        for (Currency currency : currencies) 
            if (currency.getCode().equals(code)) return currency;
        
        return null;
        
    }

    private static class JsonData {
        final Map<String, Double> rates;
        final String base;
        final LocalDate date;

        public JsonData(Map<String, Double> rates, String base, LocalDate date) {
            this.rates = rates;
            this.base = base;
            this.date = date;
        }

    }

}
