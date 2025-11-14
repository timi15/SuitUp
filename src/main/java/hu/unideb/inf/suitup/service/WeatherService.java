package hu.unideb.inf.suitup.service;

import hu.unideb.inf.suitup.dto.WeatherDto;
import hu.unideb.inf.suitup.dto.WeatherForecastDto;

import java.util.List;

public interface WeatherService {
    WeatherDto getCurrentWeather();

    List<WeatherForecastDto> getHourlyForecast();
}
