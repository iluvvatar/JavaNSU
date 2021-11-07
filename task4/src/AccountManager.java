package task4;

public interface AccountManager {
    void register(Account account) throws AccountAlreadyExistsException;
    Account login(String email, String password) throws WrongCredentialsException, AccountBlockedException;
    void removeAccount(String email, String password) throws WrongCredentialsException;
}
