package com.ex.tw.services;

import com.ex.tw.model.CityResponse;
import com.ex.tw.model.Geoname;
import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ICitiesService {

    @GET("citiesJSON")
    Single<CityResponse> queryGeonames(@Query("north") double north, @Query("south") double south,
                                       @Query("east") double east,
                                       @Query("west") double west,
                                       @Query("maxRows") int maxRows,
                                       @Query("username") String userName);
}

