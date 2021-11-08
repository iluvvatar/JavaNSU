package app;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class App {
    public static final SimpleDateFormat dateFormatCLI = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat dateFormatDefault = new SimpleDateFormat("dd/MM/yyyy");
    public static final CBR crbDB = CBR.getInstance();
    public static final LocalDB localDB = LocalDB.getInstance();

    public static void main(String[] args) {
        dateFormatCLI.setLenient(false);
//        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        if (args[0].equals("updateDB")) {
            update(args[1]);
        } else {
            System.out.println(get(args[0], args[1]));
        }
    }

    public static String get(String dateString, String code) {
        try {
            Date date = dateFormatCLI.parse(dateString);

            Currency currency = localDB.getCurrency(code, date);
            if (currency != null) {
                System.out.println("[DEBUG] Found locally");
            } else {
                System.out.println("[DEBUG] Request to CBR");
                currency = crbDB.getCurrency(code, date);
            }
            if (currency != null) {
                return currency.toString();
            } else {
                return "I couldn't find any information about this currency on the specified date.";
            }
        } catch (ParseException exc) {
            return "Invalid date. Correct example: 2021-01-01";
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }

    public static void update(String dateString) {
        Date date;
        try {
            date = dateFormatCLI.parse(dateString);
            localDB.update(date, crbDB);
        } catch (ParseException exc) {
            System.out.println("Invalid date. Correct example: 2021-01-01");
        }
    }
}
