package com.smarthome.magic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Trt> trts = new ArrayList<>();
        List<Trt> trts1 = new ArrayList<>();


        List<Trt> trts2 = new ArrayList<>();

        Trt trt = new Trt();
        trt.a = "1";
        trts2.add(trt);

        trts.addAll(trts2);
        trts1.addAll(trts2);



//        trts = trts2;
//        trts1 = trts2;

        trts.get(0).a = "2";
        trts1.get(0).a = "3";

        System.out.println(trts.get(0).a);
        System.out.println(trts1.get(0).a);


        Collections.copy(trts, trts2);
        Collections.copy(trts1, trts2);

        trts.get(0).a = "2";

        System.out.println(trts.get(0).a);
        System.out.println(trts1.get(0).a);

    }


    public static class Trt {
        public String a = "1";
    }
}
