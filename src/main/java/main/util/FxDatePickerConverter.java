package main.util;

import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FxDatePickerConverter extends StringConverter<LocalDate> {

    // Default Date Pattern
    private String pattern = "dd-MM-yyyy";
    private String fastPattern = "d/M/u";
    private String fasterPattern = "ddMMyyyy";
    // The Date Time Converter
    DateTimeFormatter dtFormatter;
    DateTimeFormatter fastFormatter;
    DateTimeFormatter fasterFormatter;

    public FxDatePickerConverter()
    {
        dtFormatter = DateTimeFormatter.ofPattern(pattern);
        fastFormatter = DateTimeFormatter.ofPattern(fasterPattern);
        fasterFormatter = DateTimeFormatter.ofPattern(fasterPattern);
    }

    public FxDatePickerConverter(String pattern)
    {
        this.pattern = pattern;
        this.fastPattern = pattern;
        this.fasterPattern = pattern;
        dtFormatter = DateTimeFormatter.ofPattern(pattern);
        fastFormatter = DateTimeFormatter.ofPattern(pattern);
        fasterFormatter = DateTimeFormatter.ofPattern(pattern);
    }

//     Change String to LocalDate
    public LocalDate fromString(String text)
    {
        LocalDate date = null;

        if (text != null && !text.trim().isEmpty()) {
            try {
                date = LocalDate.parse(text, fastFormatter);
            } catch (DateTimeException ignored) {
            }

            try {
                date = LocalDate.parse(text, fastFormatter);
            } catch (DateTimeException ignored) {
            }
            try {
                date = LocalDate.parse(text, dtFormatter);
            } catch (DateTimeException ignored) {
            }
        }

        return date;
    }

//     Change LocalDate to String
    public String toString(LocalDate date)
    {
        String text = null;

        if (date != null)
        {
            text = dtFormatter.format(date);
        }

        return text;
    }



}
