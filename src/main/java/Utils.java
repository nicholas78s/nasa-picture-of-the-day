import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

public class Utils {
    public static String getURL(String nasaURL) {
        CloseableHttpClient client = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        HttpGet request = new HttpGet(nasaURL);

        try {
            CloseableHttpResponse response = client.execute(request);
            NasaAnswer nasaAnswer = mapper.readValue(response.getEntity().getContent(), NasaAnswer.class);
            return nasaAnswer.getUrl();
        } catch (IOException ex) {
            System.out.println("Ошибка");
        }

        return "";
    }


}
