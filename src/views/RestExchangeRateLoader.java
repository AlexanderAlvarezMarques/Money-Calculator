package views;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        
        try {
            return generator(new ArrayList<>());
        } catch (Exception e) {
            return null;
        }
    }

    private List<ExchangeRate> generator(ArrayList<ExchangeRate> list) throws Exception {

        Date exchangeDate = new Date();
        
        for (int i = 0; i < currencies.size(); i++) {
            
            HashMap<String, Double> rates = getRates(currencies.get(i).getCode());
            
            for (int j = 0; j < currencies.size(); j++) {
                
                if (currencies.get(i).equals(currencies.get(j))) continue;
                
                list.add(new ExchangeRate(
                        exchangeDate,
                        rates.get(currencies.get(j).getCode()),
                        currencies.get(i),
                        currencies.get(j)
                    ));
            }
        }
        
        return list;
    }

    private HashMap<String, Double> getRates(String base) {
        
        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest?base=" + base);
        
            JsonRates jsonRates = getJsonRates(url);
            
            return jsonRates.getRates();
            
        } catch (IOException e) {
            System.out.println("Error en la lectura del fichero JSON");
            System.out.println("Error del sistema:\n" + e.getMessage());
        }
        
        return null;
    }

    private JsonRates getJsonRates(URL url) throws IOException {
        
        String jsonRead;
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            jsonRead = reader.readLine();
        }
        
        return new Gson().fromJson(jsonRead, JsonRates.class);
    }
}

class JsonRates {
    
    private final HashMap<String, Double> rates;
    private final String base;
    private final String symbol;

    public JsonRates(HashMap<String, Double> rates, String base, String symbol) {
        this.rates = rates;
        this.base = base;
        this.symbol = symbol;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public String getBase() {
        return base;
    }

    public String getSymbol() {
        return symbol;
    }
}
