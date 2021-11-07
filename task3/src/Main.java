package task3;

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
        for (Record rec : records) {
            try {
                book.createRecord(rec);
                System.out.print("Record has been added successfully: ");
                rec.print();
            } catch (PhoneNumberAlreadyExists e) {
                System.out.println(e);
            }
        }
        book.print();
        System.out.println();

        // updateRecord
        ArrayList<Record> records_upd = new ArrayList<>();
        records_upd.add(new Record(2, "+7 987 654-32-10", "Oleg"));
        records_upd.add(new Record(2, "", null));
        records_upd.add(new Record(4, "+7 987 654-32-10", "Oleg"));
        for (Record rec : records_upd) {
            try {
                book.updateRecord(rec);
                System.out.print("Record has been updated successfully: ");
                rec.print();
            } catch (RecordNotFound | RecordNotValid e) {
                System.out.println(e);
            }
        }
        book.print();
        System.out.println();

        // deleteRecord
        ArrayList<Long> ids_del = new ArrayList<>();
        ids_del.add((long) 1);
        ids_del.add((long) 5);
        for (long id : ids_del) {
            try {
                Record rec = book.deleteRecord(id);
                System.out.print("Record has been deleted successfully: ");
                rec.print();
            } catch (RecordNotFound e) {
                System.out.println(e);
            }
        }
        book.print();
        System.out.println();
    }
}
