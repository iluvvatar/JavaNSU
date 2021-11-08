package app;

import java.util.ArrayList;
import java.util.Date;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CBR extends DB {
    public static CBR instance = null;
    String url = "http://www.cbr.ru/scripts/XML_daily.asp";

    private CBR(){}

    public static synchronized CBR getInstance() {
        if (instance == null) {
            instance = new CBR();
        }
        return instance;
    }

    public Currency getCurrency(String code, Date date) {
        ArrayList<Currency> currencies = this.getCurrencies(date);
        for (Currency currency : currencies) {
            if (currency.getCode().equals(code)) {
                return currency;
            }
        }
        return null;
    }

    public ArrayList<Currency> getCurrencies(Date date) {
        String dateString = App.dateFormatDefault.format(date);
        String xml;
        try {
            HttpResponse<String> response = Unirest.get(url)
                    .queryString("date_req", dateString)
                    .asString();
            xml = response.getBody();
        } catch (UnirestException exc) {
            throw new RuntimeException(exc);
        }
        return XMLHandler.parseCurrencies(xml);
    }
}
