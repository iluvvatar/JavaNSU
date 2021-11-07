package task4;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Account {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    private String fullName;
    private LocalDate dateOfBirth;
    private String email;
    private int hashedPassword;
    private boolean blocked;

    public Account(String fullName, String dateOfBirth, String email, String password) {
        this.fullName = fullName;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, Account.dateFormatter);
        this.email = email;
        this.hashedPassword = password.hashCode();
        this.blocked = false;
    }

    public Account(String fullName, String dateOfBirth, String email, String password, boolean blocked) {
        this.fullName = fullName;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, Account.dateFormatter);
        this.email = email;
        this.hashedPassword = password.hashCode();
        this.blocked = blocked;
    }

    public Account(String fullName, String dateOfBirth, String email, int hashedPassword, boolean blocked) {
        this.fullName = fullName;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, Account.dateFormatter);
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.blocked = blocked;
    }

    public boolean login(int hashedPassword){
        return getHashedPassword() == hashedPassword;
    }

    public static Account fromCsv(String csvLine) {
        String[] fields = csvLine.split(";");

        return new Account(
                fields[0],
                fields[1],
                fields[2],
                Integer.parseInt(fields[3]),
                Boolean.parseBoolean(fields[4])
        );
    }

    public String toCsv() {
        return fullName + ";" +
                dateOfBirth.format(dateFormatter) + ";" +
                email + ";" +
                hashedPassword + ";" +
                blocked;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHashedPassword() {
        return hashedPassword;
    }

    public void getHashedPassword(int hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
