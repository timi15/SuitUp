package hu.unideb.inf.suitup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecastDto {
    private String time;
    private int temperature;
    private String iconUrl;
}
