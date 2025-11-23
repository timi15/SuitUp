package hu.unideb.inf.suitup.controller;

import hu.unideb.inf.suitup.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final WeatherService weatherService;

    @ModelAttribute("weather")
    public Object addWeatherToModel() {
        return weatherService.getCurrentWeather();
    }

    @ModelAttribute("currentDate")
    public String currentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, yyyy. MMMM d.", new Locale("hu"));
        return LocalDate.now().format(formatter);
    }
}
