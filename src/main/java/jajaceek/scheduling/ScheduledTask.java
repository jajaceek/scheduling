package jajaceek.scheduling;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ScheduledTask {

    @Scheduled(fixedRate = 5000) // uruchamia po 5 s niezależnie czy poprzedni się zakończył
//    @Scheduled(fixedDelay = 5000) // uruchamia po zakończeniu poporzedniego
//    @Scheduled(cron = ) // planuje działania
    public void sayJoke() throws IOException, InterruptedException {


        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.chucknorris.io/jokes/random")).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());
        String joke = jsonObject.getString("value");


        System.out.println(joke);
    }
}
