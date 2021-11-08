package app;

import java.util.Date;

public abstract class DB {
    public abstract Currency getCurrency(String code, Date date);
}
