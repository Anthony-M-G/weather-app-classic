package com.cr.app.weatherapp.Controller;

import com.cr.app.weatherapp.Client.Entity.WeatherResponse;

import com.cr.app.weatherapp.Client.Service.IWeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;

@RequiredArgsConstructor
@RequestMapping("/weather")
@Controller
public class WeatherController {

    private final IWeatherService weatherService;

    @GetMapping()
    public String getWeather(@RequestParam(defaultValue = "New York")  String city, Model model) throws JsonProcessingException {
        WeatherResponse weatherResponse = (WeatherResponse) weatherService.getWeather(city);
        System.out.println(weatherResponse);
        model.addAttribute("weather", weatherResponse);
        return "App";
    }
}
