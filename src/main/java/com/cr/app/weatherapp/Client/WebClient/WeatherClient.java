package com.cr.app.weatherapp.Client.WebClient;

import com.cr.app.weatherapp.Client.Entity.WeatherResponse;
import com.cr.app.weatherapp.Client.Service.IWeatherService;
import com.cr.app.weatherapp.Exceptions.HandlerEx;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherClient implements IWeatherService {
    private final HandlerEx handlerEx;
    private final RestTemplate restTemplate;
    private String URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final String appid = "&appid=06d518f3d39c3494b1c6eea33e851147";

    @Override
    public Object getWeather(String city) {
        try {
            Object weather = restTemplate.getForObject(URL + city + appid, Object.class);
            WeatherResponse weatherResponse = new WeatherResponse().builder()
                    .name(((Map<String, String>) weather).get("name"))
                    .temperature(((Map<String, Map<String, Double>>) weather).get("main").get("temp")-273.15)
                    .humidity(((Map<String, Map<String, Integer>>) weather).get("main").get("humidity"))
                    .pressure(((Map<String, Map<String, Integer>>) weather).get("main").get("pressure"))
                    .windSpeed(((Map<String, Map<String, Double>>) weather).get("wind").get("speed"))
                    .description(((Map<String, List<Map<String, String>>>) weather).get("weather").get(0).get("description"))
                    .latitude(((Map<String, Map<String, Double>>) weather).get("coord").get("lat"))
                    .longitude(((Map<String, Map<String, Double>>) weather).get("coord").get("lon"))
                    .build();
            weatherResponse.setTemperature(Math.round( weatherResponse.getTemperature() *100.0)/100.0);
            Map<String, String> weatherMatchers = new HashMap<>();
            weatherMatchers.put("clear", "/Imgs/sunny.png");
            weatherMatchers.put("clouds", "/Imgs/cloudy.png");
            weatherMatchers.put("rain", "/Imgs/rainy.png");
            weatherMatchers.put("fog", "/Imgs/fog.png");
            weatherMatchers.put("snow", "/Imgs/snow.png");
            weatherMatchers.forEach((a,b)-> {
                if (weatherResponse.getDescription().contains(a))
                    weatherResponse.setBackgroundImg(b);
            });



            return weatherResponse;
        } catch (RestClientException e) {
            log.error("Error while fetching weather data: {}", e.getMessage());
            return handlerEx.handleRestClientException(e);
        }
    }
}
