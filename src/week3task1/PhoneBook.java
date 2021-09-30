package week3task1;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Record> records;

    public PhoneBook(){
        this.records = new ArrayList<>();
    }

    public List<Record> getAllRecords(){
        return this.records;
    }

    public void createRecord(Record record) throws PhoneNumberAlreadyExists {
        if(this.containsPhoneNumber(record.getPhoneNumber())){
            throw new PhoneNumberAlreadyExists("Phone number " + record.getPhoneNumber() + " already exists.");
        }
        else{
            this.records.add(record);
        }
    }

    public void updateRecord(Record record) throws RecordNotValid, RecordNotFound {
        if (record.getName().isEmpty() || record.getPhoneNumber().isEmpty()){
            throw new RecordNotValid("Record has empty name or phone. name='" + record.getName() + "' phone='" + record.getPhoneNumber() + "'.");
        }
        int i = this.findRecordById(record.getId());
        if(i == -1){
            throw new RecordNotFound("Record with id=" + record.getId() + " not found");
        }
        else{
            this.records.set(i, record);
        }
    }

    public void deleteRecord(long id) throws RecordNotFound{
        int i = this.findRecordById(id);
        if(i == -1){
            throw new RecordNotFound("Record with id=" + id + " not found");
        }
        else{
            this.records.remove(i);
        }
    }

    public void print(){
        System.out.println("PhoneBook:");
        for (Record rec : this.records) {
            System.out.println(rec.getId() + " " + rec.getName() + " " + rec.getPhoneNumber());
        }
    }

    private boolean containsPhoneNumber(String phoneNumber){
        for (Record rec : this.records){
            if(rec.getPhoneNumber().equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }
    private int findRecordById(long id){
        for (int i = 0; i < this.records.size(); i++){
            if(this.records.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
}