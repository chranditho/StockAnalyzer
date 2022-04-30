package stockanalyzer.ctrl;

import java.util.Arrays;
import java.util.List;

import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.Result;

public class Controller {

    public void process(String ticker) {
        System.out.println("Start process");
        QuoteResponse quoteResponse = (QuoteResponse) getData(ticker);
        try {
            double highestBidPrice = quoteResponse
                    .getResult()
                    .stream()
                    .mapToDouble(Result::getBid)
                    .max().orElseThrow(() -> new Exception("Could not find highest bid price"));
            double averageBidPrice = quoteResponse
                    .getResult()
                    .stream()
                    .mapToDouble(Result::getBid)
                    .average().orElseThrow(() -> new Exception("Could not find average bid price"));
            double bidPriceCount = quoteResponse
                    .getResult()
                    .stream()
                    .mapToDouble(Result::getBid)
                    .count();

            System.out.println("highest bid price: " + highestBidPrice);
            System.out.println("average bid price: " + averageBidPrice);
            System.out.println("bid price count: " + bidPriceCount);
        } catch (Exception e) {
            System.out.println("Error while fetching asset for symbol ");
            System.out.println(e);
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
