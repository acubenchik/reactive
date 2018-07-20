package com.ex.tw;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test {

    private static void threadModelAllInMain(){
        Flowable<Integer> flow = Flowable.range(1, 5);
        Flowable<Integer> flow1 = flow.map(x-> {

            System.out.println(Thread.currentThread().getName() + " Received " + x);
            return x + 10;
        });
        flow1.subscribe(x -> System.out.println(Thread.currentThread().getName() + " Received in subscribe " + x));
    }

    private static void threadModelSubscribeOn(){
       Flowable.range(1, 5).subscribeOn(Schedulers.computation()).map(x-> {

            System.out.println(Thread.currentThread().getName() + " Received " + x);
            return x + 10;
        }).subscribe(x -> System.out.println(Thread.currentThread().getName() + " Received in subscribe " + x));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void threadModelObserveOn(){
        Flowable.range(1, 5).map(x-> {

            System.out.println(Thread.currentThread().getName() + " Received " + x);
            return x + 10;
        }).observeOn(Schedulers.computation()).subscribe(x -> System.out.println(Thread.currentThread().getName() + " Received in subscribe " + x));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void zipExample(){
        Flowable<Integer> flow1 = Flowable.range(1, 10);
        Flowable<Integer> flow2 = Flowable.range(1, 10);
        Flowable<Long> flow3 = Flowable.interval(100, TimeUnit.MILLISECONDS);
        Flowable<Long> flow4 = Flowable.interval(1, TimeUnit.SECONDS);
//        Flowable.zip(flow1, flow2, (i,k) -> i+" " + k).subscribe(System.out::println);
        Flowable.zip(flow3, flow4, (i, k) -> i+" " + k).subscribe(System.out::println);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void mergeExample(){
        Flowable<String> flow1 = Flowable.interval(100, TimeUnit.MILLISECONDS).map(i -> "L"+i);
        Flowable<String> flow2 = Flowable.interval(1, TimeUnit.SECONDS).map(i -> "R"+i);
        Flowable.merge(flow1, flow2).subscribe(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private static void concatExample(){
        Flowable<String> flow1 = Flowable.interval(100, TimeUnit.MILLISECONDS).map(i -> "L"+i);
        Flowable<String> flow2 = Flowable.interval(1, TimeUnit.SECONDS).map(i -> "R"+i);
        Flowable.concat(flow1, flow2).subscribe(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void combineLatestExample(){
        Flowable<String> flow1 = Flowable.interval(100, TimeUnit.MILLISECONDS).map(i -> "L"+i);
        Flowable<String> flow2 = Flowable.interval(1, TimeUnit.SECONDS).map(i -> "R"+i);
        Flowable.combineLatest(flow1, flow2, (i, k) -> i+k).subscribe(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void flatMapExample(){
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5,6);
//        Observable.just(list1, list2).
//                flatMap(i -> Observable.fromArray(i.toArray(new Integer[1]))).map(i -> i+1).
//                subscribe(System.out::println);


        //switchmap return only latest inner obsevable, if outer produces new
      Flowable.range(1,5)
              .switchMap(integer -> Flowable.just(integer).delay(10, TimeUnit.MICROSECONDS))
              .subscribe(System.out::println);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static  void scanExample(){
        List<Integer> res = new ArrayList<>();
        Flowable.range(1,5).scan((i, k)->i+k).collect(() -> res, (l, p) -> l.add(p)).subscribe();

    }

    public static void main(String[] args) {
//        threadModelAllInMain();
//        threadModelObserveOn();
//        zipExample();
//        mergeExample();
//        concatExample();
//        combineLatestExample();
        flatMapExample();
//        scanExample();

    }
}
