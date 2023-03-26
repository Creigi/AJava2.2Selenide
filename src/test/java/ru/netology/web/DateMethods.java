package ru.netology.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateMethods {
    public String inputDate(int days, String pattern) {
        DateTimeFormatter form = DateTimeFormatter.ofPattern(pattern);
        LocalDate currentDate = LocalDate.now().plusDays(days);
        String inputDate = currentDate.format(form);
        return inputDate;
    }
}
