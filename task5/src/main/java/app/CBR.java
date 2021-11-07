package app;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CBR {
    public static Currency getExchangeRate(String code, Date date) throws
            UnirestException, IOException, SAXException, ParserConfigurationException {
        String dateString = App.dateFormat.format(date);
        ArrayList<Currency> currencies = CBR.getExchangeRates(dateString);
        for (Currency currency : currencies){
            if (currency.getCode().equals(code)) {
                return currency;
            }
        }
        return null;
    }

    private static ArrayList<Currency> getExchangeRates(String date) throws
            UnirestException, IOException, SAXException, ParserConfigurationException {
        HttpResponse<String> response = Unirest.get("http://www.cbr.ru/scripts/XML_daily.asp")
                .queryString("date_req", date)
                .asString();
        String xml = response.getBody();
        return XMLHandler.getCurrencies(xml);
    }
}
