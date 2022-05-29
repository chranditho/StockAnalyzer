package downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import stockanalyzer.ui.UserInterface;

public class ParallelDownloader extends Downloader {

    @Override
    public int process(List<String> tickers) {
        List<Future<String>> downloads = getDownloads(tickers);
        return getDownloadCount(downloads);
    }

    private int getDownloadCount(List<Future<String>> downloads) {
        int count = 0;
        for (Future<String> futures : downloads) {
            try {
                if (futures.get() != null) {
                    count++;
                }
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e.getMessage());
            }
        }
        return count;
    }

    private List<Future<String>> getDownloads(List<String> tickers) {
        List<Future<String>> downloads = new ArrayList<>();

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        for (String ticker : tickers) {
            downloads.add(executorService.submit(() -> saveJson2File(ticker)));
        }
        return downloads;
    }
}
