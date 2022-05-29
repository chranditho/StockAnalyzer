package stockanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import downloader.SequentialDownloader;
import stockanalyzer.ctrl.Controller;
import yahooApi.YahooFinanceException;

public class UserInterface {

    private Controller ctrl = new Controller();

    public void getDataFromCtrl1() {
        try {
            ctrl.process("AAPL,AMZN,BABA");
        } catch (YahooFinanceException e) {
            throw new RuntimeException(e);
        }
    }

    public void getDataFromCtrl2() {
        try {
            ctrl.process("AMZN");
        } catch (YahooFinanceException e) {
            throw new RuntimeException(e);
        }
    }

    public void getDataFromCtrl3() {
        try {
            ctrl.process("BABA");
        } catch (YahooFinanceException e) {
            throw new RuntimeException(e);
        }
    }

    public void getDataFromCtrl4() {
        try {
            ctrl.process("loremipsum");
        } catch (YahooFinanceException e) {

        }
    }

    public void getDataForCustomInput() {
        try {
            ctrl.process(readLine());
        } catch (YahooFinanceException e) {
            throw new RuntimeException(e);
        }
    }


    public void downloadTickers(){
        SequentialDownloader sequentialDownloader = new SequentialDownloader();
        ArrayList<String> tickers = new ArrayList();
        tickers.add("AAPL");
        tickers.add("AAPL");
        tickers.add("BABA");
        sequentialDownloader.process(tickers);
    }

    //link to repo: https://github.com/chranditho/StockAnalyzer
    public void start() {
        Menu<Runnable> menu = new Menu<>("User Interface");
        menu.setTitel("Wählen Sie aus:");
        menu.insert("a", "Apple Amazon Alibaba", this::getDataFromCtrl1);
        menu.insert("b", "Amazon", this::getDataFromCtrl2);
        menu.insert("c", "Alibaba", this::getDataFromCtrl3);
        menu.insert("d", "User Input:", this::getDataForCustomInput);
        menu.insert("z", "Incorrect Ticker:", this::getDataFromCtrl4);
        menu.insert("d", "Download Tickers:", this::downloadTickers);
        menu.insert("q", "Quit", null);
        Runnable choice;
        while ((choice = menu.exec()) != null) {
            choice.run();
        }
        ctrl.closeConnection();
        System.out.println("Program finished");
    }

    private void downLoadTickers() {
    }


    protected String readLine() {
        String value = "\0";
        BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            value = inReader.readLine();
        } catch (IOException e) {
        }
        return value.trim();
    }

    protected Double readDouble(int lowerlimit, int upperlimit) {
        Double number = null;
        while (number == null) {
            String str = this.readLine();
            try {
                number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
                System.out.println("Please enter a valid number:");
                continue;
            }
            if (number < lowerlimit) {
                System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
                System.out.println("Please enter a lower number:");
                number = null;
            }
        }
        return number;
    }
}
