package com.cr.app.weatherapp.Client.Entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIncludeProperties({"city", "name", "temperature", "humidity", "pressure", "windSpeed", "description", "latitude", "longitude"})
public class WeatherResponse {
    private String name;
    private Double temperature;
    private Number humidity;
    private Number pressure;
    private Number windSpeed;
    private String description;
    private Number latitude;
    private Number longitude;
    private String backgroundImg;
}
