package stockanalyzer.ctrl;

import java.util.Arrays;
import java.util.List;

import yahooApi.YahooFinance;
import yahooApi.YahooFinanceException;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.Result;

public class Controller {

    public void process(String ticker) {
        System.out.println("Start process");
        QuoteResponse quoteResponse = (QuoteResponse) getData(ticker);
        try {
            double bidPriceCount = quoteResponse
                    .getResult()
                    .stream()
                    .mapToDouble(Result::getBid)
                    .count();

            if (bidPriceCount == 0) {
                throw new YahooFinanceException("Ticker not found");
            }
            double highestBidPrice = quoteResponse
                    .getResult()
                    .stream()
                    .mapToDouble(Result::getBid)
                    .max().orElseThrow(() -> new YahooFinanceException("Could not find highest bid price"));
            double averageBidPrice = quoteResponse
                    .getResult()
                    .stream()
                    .mapToDouble(Result::getBid)
                    .average().orElseThrow(() -> new YahooFinanceException("Could not find average bid price"));

            System.out.println("highest bid price: " + highestBidPrice);
            System.out.println("average bid price: " + averageBidPrice);
            System.out.println("bid count: " + bidPriceCount);
        } catch (YahooFinanceException yfe) {
            System.err.println(yfe);
        }

    }


    public Object getData(String searchString) {
        List<String> tickers = Arrays.asList(searchString.split(","));
        YahooFinance yahooFinance = new YahooFinance();
        return yahooFinance.getCurrentData(tickers).getQuoteResponse();
    }


    public void closeConnection() {

    }
}
