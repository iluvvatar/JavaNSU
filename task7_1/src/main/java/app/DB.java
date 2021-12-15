//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class DB {
//    public static String url = "jdbc:mysql://localhost:3306/nsu_java_task7_1";
//    public static String user = "root";
//    public static String pswd = "l12345";
//
//    public static void update(String groupName, ArrayList<>){
//        ArrayList<Currency> currencies = cbrClient.getCurrencies(date);
//        insertCurrencies(currencies, date);
//    }
//
//    private void insertCurrencies(ArrayList<Currency> currencies, Date date){
//        try (Connection connection = DriverManager.getConnection(url, user, pswd)){
//            for (Currency currency : currencies) {
//                Statement statement = connection.createStatement();
//                String sqlQuery;
//
//                sqlQuery = String.format(
//                        "INSERT IGNORE INTO currencies (code, num_code, lot, name) VALUES ('%s', %d, %d, '%s');",
//                        currency.getCode(),
//                        currency.getNumCode(),
//                        currency.getLot(),
//                        currency.getName()
//                );
//                statement.executeUpdate(sqlQuery);
//
//                sqlQuery = String.format(
//                        "INSERT IGNORE INTO history (date, currency_code, value) VALUES ('%s', '%s', %f);",
//                        App.dateFormatDefault.format(date),
//                        currency.getCode(),
//                        currency.getValue()
//                );
//                statement.executeUpdate(sqlQuery);
//            }
//        }
//        catch (Exception exc){
//            throw new RuntimeException(exc);
//        }
//    }
//}
