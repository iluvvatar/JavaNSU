package week4;

public class WrongCredentialsException extends Exception{
    public WrongCredentialsException(){
        super("Wrong email or password");
    }
}
