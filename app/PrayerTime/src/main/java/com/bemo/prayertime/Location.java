package com.bemo.prayertime;

/**
 * <h3>Represents a geographical location with latitude and longitude coordinates.</h3>
 * This class is used to define a specific location on Earth.
 */
public class Location {

    // The latitude of the location in degrees.
    // Positive values indicate locations north of the equator, while negative values indicate locations south of it.
    private double latitude;

    // The longitude of the location in degrees.
    // Positive values indicate locations east of the prime meridian, while negative values indicate locations west of it.
    private double longitude;

    /**
     * Constructs a Location object with specified latitude and longitude.
     *
     * @param latitude the latitude of the location in degrees.
     * @param longitude the longitude of the location in degrees.
     */
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the latitude of this location.
     *
     * @return the latitude in degrees.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude of this location.
     *
     * @return the longitude in degrees.
     */
    public double getLongitude() {
        return longitude;
    }
}

