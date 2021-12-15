package app;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mashape.unirest.http.exceptions.UnirestException;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class App {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws
            IOException, UnirestException, ParserConfigurationException, SAXException {
        App.dateFormat.setLenient(false);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8)) {
            System.setOut(printStream);
            while (true) {
                try {
                    System.out.println("Enter the date (example: 01/01/2021):");
                    String dateString = reader.readLine();
                    if (dateString.equals("exit")) {
                        break;
                    }
                    //                String date = "05/11/2021";
                    Date date = App.dateFormat.parse(dateString);
                    System.out.println("Enter the currency code (example: USD):");
                    String currencyCode = reader.readLine();
                    if (currencyCode.equals("exit")) {
                        break;
                    }
                    //                String currencyCode = "USD";

                    Currency currency = CBR.getExchangeRate(currencyCode, date);
                    if (currency != null) {
                        String out = (currency.getLot() + " " +
                                currency.getName() + " = " +
                                currency.getValue()
                        );
                        String valueStr = Float.toString(currency.getValue());
                        char lastChar = valueStr.charAt(valueStr.length() - 1);
                        if (lastChar == '1') {
                            out += " Российский рубль";
                        } else if (lastChar == '2' || lastChar == '3' || lastChar == '4') {
                            out += " Российских рубля";
                        } else {
                            out += " Российских рублей";
                        }
                        System.out.println(out);
                    } else {
                        System.out.println("I don't know such currency code(");
                    }
                    System.out.println();
                } catch (ParseException exc) {
                    System.out.println("Invalid date. Correct example: 01/01/2021");
                }
            }
        }
    }
}
