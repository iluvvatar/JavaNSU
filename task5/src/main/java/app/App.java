import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import Currency;

public class App {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
//            String dateString = reader.readLine();
//            System.out.println(dateString);
//            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
//            String currency = reader.readLine();

            HttpResponse<String> response = Unirest.get("https://httpbin.org/get")
                    .asString();
            System.out.println(response);
            System.out.println(response.getStatus());
            System.out.println(response.getHeaders());
            System.out.println(response.getBody());
        }
//        catch (IOException exc){
//            System.out.println("IOException");
//        }
//        catch (ParseException exc){
//            System.out.println("ParseException");
//        }
        catch (UnirestException exc){
            System.out.println("UnirestException");
        }
    }
}
