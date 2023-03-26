package ru.netology.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateMethods {
    public String Date(int days) {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate currentDate = LocalDate.now().plusDays(days);
        String inputDate = currentDate.format(form);
        return inputDate;
    }

    public String Date1(int days){
        DateTimeFormatter form = DateTimeFormatter.ofPattern("d");
        LocalDate currentDate = LocalDate.now().plusDays(days);
        String inputDate = currentDate.format(form);
        return inputDate;
    }
}
