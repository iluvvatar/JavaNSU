package app;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLHandler {
    public static ArrayList<Currency> parseCurrencies(String xml) {
        try {
            InputSource xmlIS = new InputSource(new StringReader(xml));

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(xmlIS);
            NodeList currenciesXML = document.getDocumentElement().getElementsByTagName("Valute");

            ArrayList<Currency> currencies = new ArrayList<>();
            for (int i = 0; i < currenciesXML.getLength(); i++) {
                Node currencyXML = currenciesXML.item(i);
                Currency currency = XMLHandler.parseCurrency(currencyXML);
                currencies.add(currency);
            }
            return currencies;
        }
        catch (Exception exc){
            throw new RuntimeException(exc);
        }
    }

    public static Currency parseCurrency(Node currencyXML){
        NodeList fieldsXML = currencyXML.getChildNodes();
        HashMap<String, String> fields = new HashMap<>();
        for (int j = 0; j < fieldsXML.getLength(); j++) {
            Node field = fieldsXML.item(j);
            String key = field.getNodeName();
            String value = field.getTextContent();
            fields.put(key, value);
        }
        int numCode = Integer.parseInt(fields.get("NumCode"));
        String code = fields.get("CharCode");
        int lot = Integer.parseInt(fields.get("Nominal"));
        String name = fields.get("Name");
        float value = Float.parseFloat(fields.get("Value").replace(",", "."));
        return new Currency(numCode, code, lot, name, value);
    }
}
