package task4;

public class Main {
    public static void main(String[] args) {
        Account acc1 = new Account("Malakhov Ilya Pavlovich", "2001-03-06", "mail@gmail.com", "12345");
        Account acc2 = new Account("Napoléon Bonaparte", "1769-08-15", "bonaparte@gmail.com", "qwerty");
        Account acc3 = new Account("Serg Dur Dachik", "1980-10-20", "mail1@mail.ru", "12345");
        Account acc4 = new Account("Malakhov Ilya Pavlovich", "2001-03-06", "mail@gmail.com", "12345");

        FileAccountManager manager = new FileAccountManager();
        try {
            manager.register(acc1);
            manager.register(acc2);
            manager.register(acc3);
            manager.register(acc4);
        } catch (AccountAlreadyExistsException e) {
            System.out.println(e);
        }

        try {
            manager.removeAccount(acc3.getEmail(), "12345");
            System.out.println(acc3.getEmail() + " deleted");
            manager.removeAccount(acc1.getEmail(), "wrong password");
            System.out.println(acc3.getEmail() + " deleted");
        } catch (WrongCredentialsException e) {
            System.out.println(e);
        }

        for (int i = 0; i < 6; i++) {
            System.out.print("Login " + acc2.getEmail() + " " + (i + 1) + " try. ");
            try {
                manager.login(acc2.getEmail(), "wrong password");
            } catch (WrongCredentialsException | AccountBlockedException e) {
                System.out.println(e);
            }
        }
        try {
            manager.login(acc2.getEmail(), "qwerty");
        } catch (WrongCredentialsException | AccountBlockedException e) {
            System.out.println(e);
        }
    }


}
