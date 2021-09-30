package week3task1;

public class Record {
    private long id;
    private String phoneNumber;
    private String name;

    public Record(long id, String phoneNumber, String name) {
        this.setId(id);
        this.setPhoneNumber(phoneNumber);
        this.setName(name);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void print() {
        System.out.println(this.getId() + " " + this.getName() + " " + this.getPhoneNumber());
    }
}
