package hu.unideb.inf.suitup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {
    private int temperature;
    private double windSpeed;
    private String weatherCode,iconUrl;
}
