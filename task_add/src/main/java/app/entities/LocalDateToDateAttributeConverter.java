package app.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter
public class LocalDateToDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        System.out.println("LocalDate -> Date");
        System.out.println(locDate);
        System.out.println(Date.valueOf(locDate));
        return locDate == null ? null : Date.valueOf(locDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        System.out.println("Date -> LocalDate");
        System.out.println(sqlDate);
        System.out.println(sqlDate.toLocalDate());
        return sqlDate == null ? null : sqlDate.toLocalDate();
    }
}