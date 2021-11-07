package task4;

public class AccountAlreadyExistsException extends Exception{
    public AccountAlreadyExistsException(String email){
        super("Account " + email + " already exists");
    }
}
