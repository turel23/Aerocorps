import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class LocalTimeDateFormat {
    public static LocalDateTime changeToLocalTime(String dateTimeString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");
    
    try {
        TemporalAccessor temporal = formatter.parse(dateTimeString);
        return LocalDateTime.of(
            Year.now().getValue(), // Current year
            
            temporal.get(ChronoField.MONTH_OF_YEAR),
            temporal.get(ChronoField.DAY_OF_MONTH),
            temporal.get(ChronoField.HOUR_OF_DAY),
            temporal.get(ChronoField.MINUTE_OF_HOUR)
        );

    } catch (DateTimeParseException e) {
        return null;
    }
}
}
