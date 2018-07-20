package com.ex.tw;

import com.ex.tw.services.CitiesService;
import io.reactivex.schedulers.Schedulers;

public class Main {
    private static CitiesService citiesService = new CitiesService();

    public static void main(String[] args) throws InterruptedException {
//        citiesService.getCities().buffer(1).forEach(item -> System.out.println(item));
        citiesService.getCitiesHot().onBackpressureDrop().observeOn(Schedulers.computation()).forEach(System.out::println);
        Thread.sleep(10000);

    }
}
