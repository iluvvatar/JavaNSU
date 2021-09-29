package week3task1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        ArrayList<Record> records = new ArrayList<>();
        records.add(new Record(1, "+7 913 913-13-13", "Imyarek"));
        records.add(new Record(2, "+7 913 913-13-14", "Alisa"));
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
        Record rec_upd = new Record(2, "+7 987 654-32-10", "Oleg");
        try {
            book.updateRecord(rec_upd);
        }
        catch (RecordNotFound e){
            System.out.println("Record with id " + rec_upd.getId() + " not found.");
        }
        catch (RecordNotValid e){
            System.out.println("Record has empty name or phone. name='" + rec_upd.getName() + "' phone='" + rec_upd.getPhoneNumber() + "'.");
        }
        book.print();

        // deleteRecord
        long id_del = 1;
        try {
            book.deleteRecord(1);
        }
        catch (RecordNotFound e){
            System.out.println("Record with id " + rec_upd.getId() + " not found.");
        }
        book.print();
    }
}
