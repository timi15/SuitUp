package hu.unideb.inf.suitup.util;

import java.time.LocalDate;
import java.time.Month;

public class SeasonUtils {

    public static String getCurrentSeason() {
        Month month= LocalDate.now().getMonth();

        return switch (month){
            case DECEMBER,JANUARY,FEBRUARY ->"téli";
            case MARCH,APRIL,MAY ->"tavaszi";
            case JUNE,JULY,AUGUST ->"nyári";
            case SEPTEMBER,OCTOBER,NOVEMBER ->"őszi";
        };
    }
}
