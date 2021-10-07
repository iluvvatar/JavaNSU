package week4;

import java.util.HashMap;

public class FailedLoginCounter {
    private static String email;
    private static final HashMap<String, Integer> counter = new HashMap<>();

    public static void increment(String email) throws AccountBlockedException {
        if (!counter.containsKey(email)) {
            FailedLoginCounter.counter.put(email, 0);
        }
        int attempts = FailedLoginCounter.counter.get(email) + 1;
        FailedLoginCounter.counter.put(email, attempts);
        if (attempts > 5) {
            throw new AccountBlockedException();
        }
    }
}
