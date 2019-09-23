package views;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Currency;

/**
 * @author Alexander Alvarez Marques
 * @version 20-sep-2019
 */
public class CsvCurrencyLoader implements CurrencyLoader {

    private final String filename;

    public CsvCurrencyLoader(String filename) {
        this.filename = filename;
    }
    
    @Override
    public List<Currency> load() {
        try {
            return read(new ArrayList<>());
        } catch (IOException ex) {
            Logger.getLogger(CsvCurrencyLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<Currency> read (ArrayList<Currency> list) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line  = reader.readLine();
            if (line == null) return list;
            list.add(parse(line));
        }
    }
    
    private Currency parse(String line) {
        String[] split = line.split(",");
        return new Currency(split[0], split[1], split[2]);
    }
}
