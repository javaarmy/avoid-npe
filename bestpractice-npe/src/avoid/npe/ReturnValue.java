package avoid.npe;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ReturnValue {
    public static void main(String[] args) throws Exception {


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create("http://google.com/"))
              .build();
        
        HttpResponse<String> response = null;
        try {
            response = client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            
            e.printStackTrace();
        }
        //Avoid
            System.out.println(response.statusCode());
          
        //Adopt
        if (response != null ) {
            System.out.println(response.statusCode());
        }
    }
}