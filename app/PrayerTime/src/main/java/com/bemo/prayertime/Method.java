package com.bemo.prayertime;

public class Method {
    // دالة لحساب اليوم الجولياني
    public static double calculateJulianDay(int year, int month, int day) {
        return (367 * year) - Math.floor(7 * (year + (double) ((month + 9) / 12))/ 4)
                + (double) (275 * month / 9) +day - 730531.5 ;
    }

    // دالة لحساب طول الشمس الوسطى L
    public static double calculateMeanLongitude(double D) {
        return normalizeAngle((280.461 + 0.9856474 * D));
    }

    // دالة لحساب حصة الشمس الوسطى M
    public static double calculateMeanAnomaly(double D) {
        double M =( 357.528 + 0.9856003 * D);
        return normalizeAngle(M);
    }

    // دالة لحساب طول الشمس البروجى Lambda
    public static double calculateTrueLongitudeLambda(double L, double M) {
        return normalizeAngle(L + 1.915 * Math.sin(Math.toRadians(M)) + 0.02 * Math.sin(Math.toRadians(2 * M)));
    }

    // دالة لحساب ميل دائرة البروج Obliquity
    public static double calculateObliquity(double D) {
        return 23.439 - 0.0000004 * D;
    }

    // دالة لحساب المطلع المستقيم Alpha
    public static double calculateRightAscensionAlpha(double obliquity, double lambda) {
        double Alpha =Math.atan (Math.cos(obliquity*Math.PI/180)*Math.tan(lambda*Math.PI/180)) *180/Math.PI;
        Alpha =normalizeAngle(Alpha);
        Alpha =Alpha+ 90*(Math.floor(lambda/90)-Math.floor(Alpha/90));
        return Alpha;
    }

    // دالة لحساب الزمن النجمى ST
    public static double calculateSiderealTimeST(double D) {
        return normalizeAngle(100.46 + 0.985647352 * D);
    }

    // دالة لحساب ميل الشمس الزاوي Dec
    public static double calculateDeclinationDS(double obliquity, double lambda) {
        return Math.asin(Math.sin(Math.toRadians(obliquity)) * Math.sin(Math.toRadians(lambda)))*180/Math.PI;
    }

    // دالة لحساب زوال الشمس الوسطى Noon
    public static double calculateSolarNoon(double alpha, double st) {
        return normalizeAngle(alpha - st);
    }

    // دالة لحساب الزوال العالمي UT Noon
    public static double calculateUTNoon(double noon, double longitude) {
        return (noon - longitude);
    }

    // دالة لحساب الزوال المحلي Local Noon
    public static double calculateLocalNoon(double utNoon, double zone) {
        return utNoon / 15 + zone;
    }



    // دالة لحساب ارتفاع الشمس لوقت صلاة العصر
    public static double calculateAsrAltitude(double latitude, double declination, boolean isHanafi) {
        if (isHanafi) {
            return Math.atan((2 + Math.tan(Math.toRadians(latitude - declination)))) *180/Math.PI;
        } else {
            return Math.atan((1 + Math.tan(Math.toRadians(latitude - declination)))) *180/Math.PI;
        }
    }

    // دالة لحساب قوس العصر
    public static double calculateAsrArc(double asrAlt, double declination, double latitude) {
        return Math.acos((Math.sin(Math.toRadians(90 - asrAlt)) - Math.sin(declination*Math.PI/180) * Math.sin(latitude*Math.PI/180)) /
                (Math.cos(declination*Math.PI/180) * Math.cos(latitude*Math.PI/180)))*180/Math.PI/15;
    }

    public static double ccalculateAsrTime(double localNoon ,double asrArc){
        return localNoon +asrArc;
    }

    // دالة لحساب نصف قوس النهار
    public static double calculateDiurnalArc(double declination, double latitude) {
        return Math.acos((Math.sin(Math.toRadians(-0.8333)) - Math.sin(Math.toRadians(declination)) * Math.sin(Math.toRadians(latitude))) /
                (Math.cos(Math.toRadians(declination)) * Math.cos(Math.toRadians(latitude))))*180/Math.PI;
    }

    public static double calculateSunRise(double localNoon,double durinalArc){
        return  localNoon - (durinalArc / 15);
    }
    public static double calculateSunSet(double localNoon,double durinalArc){
        return  localNoon + (durinalArc / 15);
    }

    // دالة لحساب فضل الدائر
    public static double calculateEshaArc(double declination, double latitude) {
        return Math.acos((Math.sin(Math.toRadians(-18)) - Math.sin(Math.toRadians(declination)) * Math.sin(Math.toRadians(latitude))) /
                (Math.cos(Math.toRadians(declination)) * Math.cos(Math.toRadians(latitude))))*180/Math.PI;
    }
    public static double calculateEshaTime(double localNoon,double eshaArc){
        return  localNoon +( eshaArc /15);
    }
    // دالة لحساب فضل دائر الفجر
    public static double calculateFajrArc(double declination, double latitude) {
        return Math.acos((Math.sin(Math.toRadians(-18)) - Math.sin(Math.toRadians(declination)) * Math.sin(Math.toRadians(latitude))) /
                (Math.cos(Math.toRadians(declination)) * Math.cos(Math.toRadians(latitude))))*180/Math.PI;
    }
    public static double calculateFajrTime(double localNoon,double fajArc){
        return  localNoon -( fajArc /15);
    }
    // دالة لتطبيع الزوايا لتكون بين 0° و 360°
    public static double normalizeAngle(double angle) {
        angle = angle % 360;
        return angle < 0 ? angle + 360 : angle;
    }
}
