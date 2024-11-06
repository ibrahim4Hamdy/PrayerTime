package com.bemo.prayertime;


import java.time.Duration;
import java.time.LocalDateTime;

public class SimplePrayerTimeCalculator implements PrayerTimeCalculator{

    // Julian days count since the start of the Julian calendar
    private double D;

    // Mean longitude of the sun (sun's position on the mean orbit)
    private double L;

    // Mean anomaly of the sun (sun's angular position on the mean orbit)
    private double M;

    // True longitude of the sun (actual position of the sun on the ecliptic)
    private double Lambda;

    // Obliquity of the ecliptic (tilt angle of Earth's axis relative to its orbit)
    private double Obliquity;

    // Right ascension of the sun (the angular distance measured along the celestial equator)
    private double Alpha;

    // Sidereal time (apparent time relative to the fixed stars)
    private double ST;

    // Declination of the sun (angular distance of the sun north or south of the celestial equator)
    private double Des;

    // Solar noon (time of solar culmination or local meridian crossing)
    private double Noon;

    // Universal Time for solar noon (solar noon in Universal Time)
    private double UTNoon;

    // Local solar noon (solar noon adjusted for the observer's time zone)
    private double LocalNoon;

    // Asr altitude for Shafi'i method (sun's altitude for Asr prayer calculation in Shafi'i school)
    private double AsrAltitudeShafhi;

    // Asr altitude for Hanafi method (sun's altitude for Asr prayer calculation in Hanafi school)
    private double AsrAltitudeHanafi;

    // Asr arc for Shafi'i method (hour angle for Asr calculation in Shafi'i school)
    private double AsrArcShafhi;

    // Asr arc for Hanafi method (hour angle for Asr calculation in Hanafi school)
    private double AsrArcHanafi;

    // Diurnal arc (half of the day's arc from sunrise to sunset)
    private double DiurnalArc;

    // Isha arc (hour angle for Isha calculation)
    private double EshaArc;


    // Fajr arc (hour angle for Fajr calculation)
    private double FajrArc;

    // Location details (contains latitude and longitude)
    private final Location location;

    // Date information (contains year, month, and day)
    private final DateInfo dateInfo;

    // Year value for the date being calculated
    private int year = 2004;

    // Month value for the date being calculated
    private int month = 1;

    // Day value for the date being calculated
    private int day = 1;

    // Time zone offset from UTC
    private int zone = 2;

    // Latitude of the observer's location
    private double latitude;

    // Longitude of the observer's location
    private double longitude;


    public SimplePrayerTimeCalculator(Location location, DateInfo dateInfo) {
        this.location = location;
        this.dateInfo = dateInfo;

        // Set date and location information
        this.year = dateInfo.getYear();
        this.month = dateInfo.getMonth();
        this.day = dateInfo.getDay();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();

        // Calculate essential astronomical values
        this.D = Method.calculateJulianDay(year, month, day);
        this.L = Method.calculateMeanLongitude(D);
        this.M = Method.calculateMeanAnomaly(D);
        this.Lambda = Method.calculateTrueLongitudeLambda(L, M);
        this.Obliquity = Method.calculateObliquity(D);
        this.Alpha = Method.calculateRightAscensionAlpha(Obliquity, Lambda);
        this.ST = Method.calculateSiderealTimeST(D);
        this.Des = Method.calculateDeclinationDS(Obliquity, Lambda);

        // Calculate solar noon and its variants
        this.Noon = Method.calculateSolarNoon(Alpha, ST);
        this.UTNoon = Method.calculateUTNoon(Noon, longitude);
        this.LocalNoon = Method.calculateLocalNoon(UTNoon,zone);
        // Calculate Asr times for both Shafi'i and Hanafi methods
        this.AsrAltitudeShafhi = Method.calculateAsrAltitude(latitude, Des, false);
        this.AsrAltitudeHanafi = Method.calculateAsrAltitude(latitude, Des, true);
        this.AsrArcShafhi = Method.calculateAsrArc(AsrAltitudeShafhi, Des, latitude);
        this.AsrArcHanafi = Method.calculateAsrArc(AsrAltitudeHanafi, Des, latitude);

        // Calculate diurnal arc and other arcs needed for prayer times
        this.DiurnalArc = Method.calculateDiurnalArc(Des, latitude);
        this.EshaArc = Method.calculateEshaArc(Des, latitude);
        this.FajrArc = Method.calculateFajrArc(Des, latitude);
    }

    @Override
    public LocalDateTime getFajrTime() {
        double FajrTime = Method.calculateFajrTime(LocalNoon,FajrArc);

        DMS dms =new DMS(FajrTime);

        int d =dms.getD();
        int m =dms.getM();

        return LocalDateTime.of(year,month,day,d,m);
    }

    @Override
    public LocalDateTime getDhuhrTime() {
        double LocalNoon = Method.calculateLocalNoon(UTNoon,zone);
        DMS dms =new DMS(LocalNoon);

        int d =dms.getD();
        int m =dms.getM();

        return LocalDateTime.of(year,month,day,d,m);
    }

    @Override
    public LocalDateTime getAsrTime() {
        double AsrTimeShafhi = Method.ccalculateAsrTime(LocalNoon,AsrArcShafhi);

        double AsrTimeHanafi = Method.ccalculateAsrTime(LocalNoon,AsrArcHanafi);

        DMS dms =new DMS(AsrTimeHanafi);

        int d =dms.getD();
        int m =dms.getM();

        return LocalDateTime.of(year,month,day,d,m);
    }

    @Override
    public LocalDateTime getMaghribTime() {
        double SunSetTime = Method.calculateSunSet(LocalNoon,DiurnalArc);
        DMS dms =new DMS(SunSetTime);

        int d =dms.getD();
        int m =dms.getM();

        return LocalDateTime.of(year,month,day,d,m);
    }

    @Override
    public LocalDateTime getIshaTime() {
        double EshaTime = Method.calculateEshaTime(LocalNoon,EshaArc);
        DMS dms =new DMS(EshaTime);

        int d =dms.getD();
        int m =dms.getM();

        return LocalDateTime.of(year,month,day,d,m);
    }

    @Override
    public LocalDateTime getSunRiseTime() {
        double SunRiseTime = Method.calculateSunRise(LocalNoon,DiurnalArc);
        DMS dms =new DMS(SunRiseTime);

        int d =dms.getD();
        int m =dms.getM();

        return LocalDateTime.of(year,month,day,d,m);
    }

    @Override
    public LocalDateTime getSunSetTime() {
        double SunSetTime = Method.calculateSunSet(LocalNoon,DiurnalArc);
        DMS dms =new DMS(SunSetTime);

        int d =dms.getD();
        int m =dms.getM();

        return LocalDateTime.of(year,month,day,d,m);
    }
    @Override
    public LocalDateTime getNextPrayerTime() {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime fajr = getFajrTime();
        LocalDateTime dhuhr = getDhuhrTime();
        LocalDateTime asr = getAsrTime();
        LocalDateTime maghrib = getMaghribTime();
        LocalDateTime isha = getIshaTime();

        // التحقق من الصلاة التالية بناءً على الوقت الحالي بصيغة 24 ساعة
        if (now.isBefore(fajr)) {
            return fajr;
        } else if (now.isBefore(dhuhr)) {
            return dhuhr;
        } else if (now.isBefore(asr)) {
            return asr;
        } else if (now.isBefore(maghrib)) {
            return maghrib;
        } else if (now.isBefore(isha)) {
            return isha;
        }
            // إذا كان الوقت الحالي بعد العشاء، الصلاة التالية هي الفجر لليوم التالي

        return fajr.plusDays(1);

    }


    @Override
    public Duration getTimeUntilNextPrayer() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextPrayerTime = getNextPrayerTime();

        // حساب الفرق الزمني بين الوقت الحالي ووقت الصلاة التالية
        Duration durationUntilNextPrayer = Duration.between(now, nextPrayerTime);

        // إذا كانت الصلاة التالية في اليوم التالي، تأكد من أن الفارق موجب
        if (durationUntilNextPrayer.isNegative()) {
            // إذا كانت الصلاة التالية في اليوم التالي، قم بإضافة يوم
            nextPrayerTime = nextPrayerTime.plusDays(1);
            durationUntilNextPrayer = Duration.between(now, nextPrayerTime);
        }

        return durationUntilNextPrayer;
    }

    @Override
    public String toString() {
        return "SimplePrayerTimeCalculator{" +
                "D=" + D +
                ", L=" + L +
                ", M=" + M +
                ", Lambda=" + Lambda +
                ", Obliquity=" + Obliquity +
                ", Alpha=" + Alpha +
                ", ST=" + ST +
                ", Des=" + Des +
                ", Noon=" + Noon +
                ", UTNoon=" + UTNoon +
                ", LocalNoon=" + LocalNoon +
                ", AsrAltitudeShafhi=" + AsrAltitudeShafhi +
                ", AsrAltitudeHanafi=" + AsrAltitudeHanafi +
                ", AsrArcShafhi=" + AsrArcShafhi +
                ", AsrArcHanafi=" + AsrArcHanafi +
                ", DiurnalArc=" + DiurnalArc +
                ", EshaArc=" + EshaArc +
                ", FajrArc=" + FajrArc +
                ", location=" + location +
                ", dateInfo=" + dateInfo +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", zone=" + zone +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
