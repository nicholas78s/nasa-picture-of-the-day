import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;

import java.io.FileOutputStream;
import java.io.IOException;
//import java.util.Scanner;

public class Main {
    public static final ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws IOException {
        String url = "https://api.nasa.gov/planetary/apod?api_key=f3QvYhG7UVY3MEigiQLhJGMaf1YIj2eBLCDx853i";
//        HttpClients = new HttpClients();
//        HttpClients = new HttpClients();
        CloseableHttpClient client = HttpClients.createDefault();
//        CloseableHttpClient httpClient = HttpClientBuilder.create()
//            .setDefaultRequestConfig(RequestConfig.custom()
//                .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
//                .setSocketTimeout(30000)    // максимальное время ожидания получения данных
//                .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
//                .build())
//            .build();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = client.execute(request);
        //Scanner scanner = new Scanner(response.getEntity().getContent());
        //System.out.println(scanner.nextLine());

        Nasa nasa = mapper.readValue(response.getEntity().getContent(), Nasa.class);
        CloseableHttpResponse image = client.execute(new HttpGet(nasa.getUrl()));
        String[] nasaArray = nasa.getUrl().split("/");
        String fileName = nasaArray[nasaArray.length - 1];
        HttpEntity entity = image.getEntity();

        if (entity != null) {
            FileOutputStream fos = new FileOutputStream(fileName);
            entity.writeTo(fos);
            fos.close();
        }

        client.close();
        response.getEntity().getContent().close();
    }
}
