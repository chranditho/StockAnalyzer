package downloader;

import java.util.List;

public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<String> tickers) {
        int count = 0;
        for (String ticker : tickers) {
            String fileName = saveJson2File(ticker);
            if(fileName != null)
                count++;
        }
        return count;
    }
}
