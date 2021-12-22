package app.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter
public class DateToLocalDateAttributeConverter implements AttributeConverter<Date, LocalDate> {

    @Override
    public LocalDate convertToDatabaseColumn(Date sqlDate) {
        return sqlDate == null ? null : sqlDate.toLocalDate();
    }

    @Override
    public Date convertToEntityAttribute(LocalDate locDate) {
        return locDate == null ? null : Date.valueOf(locDate);
    }
}