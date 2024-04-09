package com.cr.app.weatherapp.Client.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface IWeatherService {

    Object getWeather(String city) throws JsonProcessingException;
}
