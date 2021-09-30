package week3task1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        ArrayList<Record> records = new ArrayList<>();
        records.add(new Record(1, "+7 913 913-13-13", "Imyarek"));
        records.add(new Record(2, "+7 913 913-13-14", "Alisa"));
        records.add(new Record(3, "+7 913 913-13-15", "Siri"));
        // PhoneNumberAlreadyExists
        records.add(new Record(3, "+7 913 913-13-15", "Siri"));

        // createRecord
        for(Record rec : records){
            try {
                book.createRecord(rec);
            }
            catch (PhoneNumberAlreadyExists e){
                System.out.println("Phone " + rec.getPhoneNumber() + " already exists.");
            }
        }
        book.print();

        // updateRecord
        ArrayList<Record> records_upd = new ArrayList<>();
        records_upd.add(new Record(2, "+7 987 654-32-10", "Oleg"));
        records_upd.add(new Record(2, "", ""));
        records_upd.add(new Record(4, "+7 987 654-32-10", "Oleg"));
        for(Record rec : records_upd){
            try {
                book.updateRecord(rec);
            }
            catch (RecordNotFound e){
                System.out.println("Record with id " + rec.getId() + " not found.");
            }
            catch (RecordNotValid e){
                System.out.println("Record has empty name or phone. name='" + rec.getName() + "' phone='" + rec.getPhoneNumber() + "'.");
            }
        }
        book.print();

        // deleteRecord
        ArrayList <Long> ids_del = new ArrayList<>();
        ids_del.add((long)1);
        ids_del.add((long)5);
        for(long id : ids_del) {
            try {
                book.deleteRecord(1);
                book.deleteRecord(5);
            } catch (RecordNotFound e) {
                System.out.println("Record with id " + id + " not found.");
            }
        }
        book.print();
    }
}
