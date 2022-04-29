package stockanalyzer.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import yahooApi.YahooFinance;

public class Controller {

    public void process(String ticker) {
        System.out.println("Start process");

        //TODO implement Error handling
        //TODO implement methods for
        List<String> tickers = new ArrayList<>();
        tickers.add(ticker);
        YahooFinance yahooFinance = new YahooFinance();
        try {
            //1) Daten laden
            var response = yahooFinance.requestData(tickers);
            System.out.println(response);
            //2) Daten Analyse
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public Object getData(String searchString) {


        return null;
    }


    public void closeConnection() {

    }
}
