package com.ex.tw.factories;

import com.ex.tw.services.ICitiesService;

public class CitiesServiceFactory {

    private static ICitiesService instance;

    public static ICitiesService getInstance() {
        if (instance == null) {
            synchronized (CitiesServiceFactory.class) {
                if (instance == null) {
                    instance = RetrofitServiceFactory.getInstance().create(ICitiesService.class);
                }
            }
        }
        return instance;
    }
}
