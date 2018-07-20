package com.ex.tw.services;

import com.ex.tw.factories.CitiesServiceFactory;
import com.ex.tw.model.Geoname;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

import java.util.ArrayList;
import java.util.List;

public class CitiesService {

    //cold source Producer, producing exactly one item at time, not overhelming Consumer (reactive pull)
    public Flowable<Geoname> getCities() {
        final List<Geoname> result = getGeonames();
        return Flowable.generate(() -> result.iterator(), (geonameIterator, emitter) -> {
            if (geonameIterator.hasNext()) {
                System.out.println("Producing an item..");
                emitter.onNext(geonameIterator.next());
            } else {
                emitter.onComplete();
            }
        });
    }

    //push model, producer generates items at it's own pace, overhelming consumer
    public Flowable<Geoname> getCitiesHot() {
        final List<Geoname> result = getGeonames();
        return Flowable.create(subscriber -> {
            int state = 0;
            while (!subscriber.isCancelled()) {
                if(state == result.size()) {
                    state = 0;
                }
                subscriber.onNext(result.get((int) state++));
            }
        }, BackpressureStrategy.ERROR);
    }

    private List<Geoname> getGeonames() {
        final List<Geoname> result = new ArrayList<>();
        CitiesServiceFactory.getInstance()
                .queryGeonames(44.1, -9.9, -22.4, 55.2, 50, "acubenchik")
                .map(res -> res.geonames).subscribe(geonames -> result.addAll(geonames));
        return result;
    }

}
