package task4;

import java.util.ArrayList;

public class FileAccountManager implements AccountManager {
    FileService service = FileService.getInstance();

    public void register(Account account) throws AccountAlreadyExistsException{
        if (getAccount(account.getEmail()) == null) {
            service.addLine(account.toCsv());
        }
        else{
            throw new AccountAlreadyExistsException(account.getEmail());
        }
    }

    public Account login(String email, String password)
            throws WrongCredentialsException, AccountBlockedException{
        Account account = getAccount(email);
        if (account == null){
            throw new WrongCredentialsException();
        }
        if (!account.login(password.hashCode())){
            try {
                FailedLoginCounter.increment(email);
            }
            catch (AccountBlockedException e){
                removeAccount(account.getEmail(), account.getHashedPassword());
                account.setBlocked(true);
                try {
                    register(account);
                }
                catch (AccountAlreadyExistsException reg_e){
                    throw new RuntimeException(reg_e);
                }
                throw e;
            }
            throw new WrongCredentialsException();
        }
        if (account.isBlocked()){
            throw new AccountBlockedException();
        }
        return account;
    }

    public void removeAccount(String email, String password) throws WrongCredentialsException {
        removeAccount(email, password.hashCode());
    }

    private void removeAccount(String email, int hashedPassword) throws WrongCredentialsException {
        Account account = nonblockingLogin(email, hashedPassword);
        service.removeLine(account.toCsv());
    }

    private Account getAccount(String email){
        ArrayList<Account> accounts = service.getAllAccounts();
        for (Account account : accounts){
            if (account.getEmail().equals(email)){
                return account;
            }
        }
        return null;
    }

    private Account nonblockingLogin(String email, String password)
            throws WrongCredentialsException {
        return nonblockingLogin(email, password.hashCode());
    }

    private Account nonblockingLogin(String email, int hashedPassword)
            throws WrongCredentialsException {
        Account account = getAccount(email);
        if (account == null || !account.login(hashedPassword)) {
            throw new WrongCredentialsException();
        }
        return account;
    }
}
