package main.util;

import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FxDatePickerConverter extends StringConverter<LocalDate> {

    // Default Date Pattern
    private String pattern = "Dd/mm/yyyy";
    // The Date Time Converter
    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(pattern);

    public FxDatePickerConverter()
    {
        dtFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    public FxDatePickerConverter(String pattern)
    {
        this.pattern = pattern;
        dtFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    // Change String to LocalDate
//    public LocalDate fromString(String text)
//    {
//        LocalDate date = null;
//
//        if (text != null && !text.trim().isEmpty())
//        {
//            date = LocalDate.parse(text, dtFormatter);
//        }
//
//        return date;
//    }

    @Override public String toString(LocalDate date) {
        if (date != null) {
            return dtFormatter.format(date);
        } else {
            return "";
        }
    }

    @Override public LocalDate fromString(String string) {
        if (string != null && !string.isEmpty()) {
            return LocalDate.parse(string, dtFormatter);
        } else {
            return null;
        }
    }

    // Change LocalDate to String
//    public String toString(LocalDate date)
//    {
//        String text = null;
//
//        if (date != null)
//        {
//            text = dtFormatter.format(date);
//        }
//
//        return text;
//    }



}
