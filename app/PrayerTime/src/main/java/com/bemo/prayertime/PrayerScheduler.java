package com.bemo.prayertime;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * The PrayerScheduler interface provides methods for calculating
 * and retrieving information about the upcoming prayer times.
 * It includes functionality to determine the duration until the
 * next prayer and to get the exact LocalDateTime of that prayer.
 */
public interface PrayerScheduler {

    /**
     * Returns the duration remaining until the next prayer time.
     * This method calculates the difference between the current
     * time and the scheduled time of the next prayer.
     *
     * @return Duration representing the time left until the next prayer.
     */
    Duration getTimeUntilNextPrayer();

    /**
     * Returns the LocalDateTime of the next prayer.
     * This method provides the specific date and time of the
     * next scheduled prayer, which can be used for display
     * or further calculations.
     *
     * @return LocalDateTime of the next prayer.
     */
    LocalDateTime getNextPrayerTime();
}

