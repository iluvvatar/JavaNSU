package week3;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Record> records;

    public PhoneBook() {
        this.records = new ArrayList<>();
    }

    public List<Record> getAllRecords() {
        return this.records;
    }

    public void createRecord(Record record) throws PhoneNumberAlreadyExists {
        if (this.containsPhoneNumber(record.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExists("Phone number " + record.getPhoneNumber() + " already exists.");
        } else {
            this.records.add(record);
        }
    }

    public void updateRecord(Record record) throws RecordNotValid {
        if (record.getName() == null || record.getPhoneNumber() == null
                || record.getName().isEmpty() || record.getPhoneNumber().isEmpty()) {
            throw new RecordNotValid("Record has unfilled name or phone. name=" + record.getName() + " phone=" + record.getPhoneNumber());
        }
        int i = this.findRecordById(record.getId());
        if (i == -1) {
            throw new RecordNotFound("Record with id=" + record.getId() + " not found");
        } else {
            this.records.set(i, record);
        }
    }

    public Record deleteRecord(long id) {
        int i = this.findRecordById(id);
        if (i == -1) {
            throw new RecordNotFound("Record with id=" + id + " not found");
        } else {
            Record rec = this.getRecordById(id);
            this.records.remove(i);
            return rec;
        }
    }

    public void print() {
        System.out.println("PhoneBook:");
        for (Record rec : this.records) {
            rec.print();
        }
    }

    public Record getRecordById(long id) {
        int i = this.findRecordById(id);
        if (i == -1) {
            return null;
        } else {
            return this.records.get(i);
        }
    }

    private boolean containsPhoneNumber(String phoneNumber) {
        for (Record rec : this.records) {
            if (rec.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    private int findRecordById(long id) {
        for (int i = 0; i < this.records.size(); i++) {
            if (this.records.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}