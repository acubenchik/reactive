package com.ex.tw.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Geoname {
    public double lat;
    public double lng;
    public long geonameId;
    public String countrycode;
    public String name;
    public String fclName;
    public String toponymName;
    public String fcodeName;
    public String wikipedia;
    public String fcl;
    public long population;
    public String fcode;
}