package com.hele.mrd.app.framework;

public class Test {

    public static void main(String[] args) {
//        double x = 467910.296;
//        double y = 2587358.731;
        double x = 0;
        double y = 2584609.82;

        double locationX = x / 20037508.3427892 * 180;
        double locationY = y / 20037508.3427892 * 180;
        locationY = 180 / Math.PI * (2 * Math.atan(Math.exp(locationY * Math.PI / 180)) - Math.PI / 2);
        System.out.println("hele>>>" + locationX + "___" +locationY);
    }
}
