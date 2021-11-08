package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class LocalDB extends DB{
    public static LocalDB instance = null;
    public static String url = "jdbc:mysql://localhost:3306/java_nsu_task6";
    public static String user = "root";
    public static String pswd = "l12345";

    public static synchronized LocalDB getInstance(){
        if (instance == null){
            instance = new LocalDB();
        }
        return instance;
    }

    public Currency getCurrency(String code, Date date){
        try (Connection connection = DriverManager.getConnection(url, user, pswd)){
            Statement statement = connection.createStatement();
            String sqlQuery = String.format(
                    "SELECT * " +
                            "FROM currencies " +
                            "INNER JOIN history " +
                            "ON currencies.code = history.currency_code " +
                            "WHERE history.date = '%s' and currencies.code = '%s';",
                    App.dateFormatDefault.format(date),
                    code
            );
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            boolean isEmpty = !resultSet.isBeforeFirst();
            if (isEmpty){
                return null;
            }
            resultSet.next();
            int numCode = resultSet.getInt("num_code");
            int lot = resultSet.getInt("lot");
            String name = resultSet.getString("name");
            float value = resultSet.getFloat("value");
            return new Currency(numCode, code, lot, name, value);
        }
        catch (Exception exc){
            throw new RuntimeException(exc);
        }
    };

    public void update(Date date, CBR cbrClient){
        ArrayList<Currency> currencies = cbrClient.getCurrencies(date);
        insertCurrencies(currencies, date);
    }

    private void insertCurrencies(ArrayList<Currency> currencies, Date date){
        try (Connection connection = DriverManager.getConnection(url, user, pswd)){
            for (Currency currency : currencies) {
                Statement statement = connection.createStatement();
                String sqlQuery;

                sqlQuery = String.format(
                        "INSERT IGNORE INTO currencies (code, num_code, lot, name) VALUES ('%s', %d, %d, '%s');",
                        currency.getCode(),
                        currency.getNumCode(),
                        currency.getLot(),
                        currency.getName()
                );
                statement.executeUpdate(sqlQuery);

                sqlQuery = String.format(
                        "INSERT IGNORE INTO history (date, currency_code, value) VALUES ('%s', '%s', %f);",
                        App.dateFormatDefault.format(date),
                        currency.getCode(),
                        currency.getValue()
                );
                statement.executeUpdate(sqlQuery);
            }
        }
        catch (Exception exc){
            throw new RuntimeException(exc);
        }
    }

}
