import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws TelegramApiException {
        new NasaTelegramBot("nic7_jdfree24_bot", "DEMO_TOKEN");
//
//        String url = "https://api.nasa.gov/planetary/apod" +
//                "?api_key=DEMO_KEY" +
//                "&date=2024-05-21";
//
//        String nasaURL = Utils.getURL(url);
//        CloseableHttpResponse urlImage = client.execute(new HttpGet(nasaAnswer.getUrl()));
//        String[] separatedAnswer = nasaAnswer.getUrl().split("/");
//        String fileName = separatedAnswer[separatedAnswer.length - 1];
//
//        HttpEntity entity = urlImage.getEntity();
//        if (entity != null) {
//            FileOutputStream fos = new FileOutputStream(fileName);
//            entity.writeTo(fos);
//            fos.close();
//        }
//
//        client.close();
//        response.getEntity().getContent().close();
    }
}
