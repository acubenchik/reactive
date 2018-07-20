package com.ex.tw.model;

import lombok.Data;

import java.util.List;

@Data
public class CityResponse {
    public List<Geoname> geonames;
}