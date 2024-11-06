package com.bemo.prayertime;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <h3>Interface for calculating prayer times based on specific calculations or location data.</h3>
 * Provides methods for retrieving times for the five daily Islamic prayers.
 */
public interface PrayerTimeCalculator extends PrayerScheduler{

    /**
     * Calculates and returns the Fajr prayer time (pre-dawn prayer).
     * This time is typically defined as when the sky starts to lighten before sunrise,
     * marking the end of the night.
     *
     * @return LocalDateTime representing the Fajr prayer time.
     */
    LocalDateTime getFajrTime();

    /**
     * Calculates and returns the Dhuhr prayer time (noon prayer).
     * This time is generally defined as when the sun passes the zenith, indicating midday.
     *
     * @return LocalDateTime representing the Dhuhr prayer time.
     */
    LocalDateTime getDhuhrTime();

    /**
     * Calculates and returns the Asr prayer time (afternoon prayer).
     * This time varies based on different Islamic school calculations but usually occurs
     * when the shadow of an object reaches a specific length.
     *
     * @return LocalDateTime representing the Asr prayer time.
     */
    LocalDateTime getAsrTime();

    /**
     * Calculates and returns the Maghrib prayer time (sunset prayer).
     * This time is defined as the moment of sunset, marking the end of the daytime.
     *
     * @return LocalDateTime representing the Maghrib prayer time.
     */
    LocalDateTime getMaghribTime();

    /**
     * Calculates and returns the Isha prayer time (evening prayer).
     * This time usually occurs after twilight, when the sky has fully darkened after sunset.
     *
     * @return LocalDateTime representing the Isha prayer time.
     */
    LocalDateTime getIshaTime();

    /**
     * Calculates and returns the time for sunrise, marking the beginning of the daylight period.
     *
     * @return LocalDateTime representing the time for sunrise.
     */
    LocalDateTime getSunRiseTime();

    /**
     * Calculates and returns the time for sunset, marking the end of the daylight period.
     *
     * @return LocalDateTime representing the time for sunset.
     */
    LocalDateTime getSunSetTime();


}
