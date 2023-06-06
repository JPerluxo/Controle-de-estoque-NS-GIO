package com.controleestoquensgio.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConversorData {

    public static String converterData (LocalDate localDate) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(dateTimeFormat);
    }
}
