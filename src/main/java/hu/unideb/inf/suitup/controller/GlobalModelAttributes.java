package hu.unideb.inf.suitup.controller;

import hu.unideb.inf.suitup.dto.WeatherForecastDto;
import hu.unideb.inf.suitup.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes {

    private final WeatherService weatherService;

    @ModelAttribute("forecast")
    public List<WeatherForecastDto> loadForecast() {
        return weatherService.getHourlyForecast();
    }
}