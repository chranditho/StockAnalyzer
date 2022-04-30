package yahooApi.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Asset {

    private static final Quote QUOTE_NULL = new Quote(new Date(), 0.0);

    private String name;

    private String symbol;

    private Date date;

    private Set<Quote> timeSeries = new HashSet<>();

    public Asset() {
    }

    public Asset(String symbol) {
        this.symbol = symbol;
    }

    public Asset(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Quote getLastQuote() {
        return null;
    }

    @Override
    public String toString() {
        return name + " (" +symbol+") "+getLastQuote();

    }

    public void addQuote(Quote quote) {
        timeSeries.add(quote);
    }
}