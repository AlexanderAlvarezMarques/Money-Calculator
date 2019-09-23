package views;

import java.util.List;

/**
 * @author Alexander Alvarez Marques
 * @version 20-sep-2019
 */
public interface ExchangeRateLoader {
    
    public List<ExchangeRate> load();
}
