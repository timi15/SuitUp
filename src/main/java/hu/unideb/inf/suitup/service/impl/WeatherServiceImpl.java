package hu.unideb.inf.suitup.service.impl;


import hu.unideb.inf.suitup.dto.WeatherDto;
import hu.unideb.inf.suitup.dto.WeatherForecastDto;
import hu.unideb.inf.suitup.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${weather.latitude}")
    private double latitude;

    @Value("${weather.longitude}")
    private double longitude;

    private WeatherDto cachedWeather;

    @Override
    public WeatherDto getCurrentWeather() {
        if (cachedWeather == null) {
            fetchWeather();
        }
        return cachedWeather;
    }

    @Scheduled(fixedRateString = "${weather.refresh.minutes:30}000", initialDelay = 1000)
    public void fetchWeather() {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.open-meteo.com/v1/forecast")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("current_weather", "true")
                .toUriString();

        Map<?, ?> response = restTemplate.getForObject(url, Map.class);
        Map<?, ?> current = (Map<?, ?>) response.get("current_weather");

        int temperature = (int) Math.round((double) current.get("temperature"));
        double windSpeed = (double) current.get("windspeed");
        int weatherCode = (int) current.get("weathercode");

        String iconUrl = getWeatherIcon(weatherCode);

        cachedWeather = new WeatherDto(temperature, windSpeed, String.valueOf(weatherCode), iconUrl);
    }

    private String getWeatherIcon(int code) {

        if (code == 0) return "/icons/sun.png";
        if (code >= 1 && code <= 3) return "/icons/partly-cloudy.png";
        if (code == 45 || code == 48) return "/icons/fog.png";
        if (code >= 51 && code <= 67) return "/icons/rain.png";
        if (code >= 71 && code <= 77) return "/icons/snow.png";
        return "/icons/cloud.png";
    }

    @Override
    public List<WeatherForecastDto> getHourlyForecast() {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.open-meteo.com/v1/forecast")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("hourly", "temperature_2m,weathercode")
                .queryParam("forecast_days", "1")
                .toUriString();

        Map<?, ?> response = restTemplate.getForObject(url, Map.class);
        Map<?, ?> hourly = (Map<?, ?>) response.get("hourly");

        List<String> times = (List<String>) hourly.get("time");
        List<Double> temps = (List<Double>) hourly.get("temperature_2m");
        List<Integer> codes = (List<Integer>) hourly.get("weathercode");

        List<WeatherForecastDto> forecast = new ArrayList<>();
        for (int i = 0; i < times.size(); i += 4) {
            String iconUrl = getWeatherIcon(codes.get(i));
            forecast.add(new WeatherForecastDto(times.get(i), temps.get(i).intValue(), iconUrl));
        }
        return forecast;
    }

}
