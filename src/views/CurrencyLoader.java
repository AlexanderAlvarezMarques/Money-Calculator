package views;

import java.util.List;
import models.Currency;

/**
 * @author Alexander Alvarez Marques
 * @version 20-sep-2019
 */
public interface CurrencyLoader {
    
    public List<Currency> load();
}
