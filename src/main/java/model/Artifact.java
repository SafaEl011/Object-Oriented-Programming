package model;

import java.util.Date;

public abstract class Artifact implements FoundItem {

    protected int id;
    protected double latitude;
    protected double longitude;
    protected int finderId;
    protected Date dateFrom;
    protected int estimatedYear;
    protected Integer museumId;

    public Artifact(int id, double latitude, double longitude, int finderId, Date dateFrom, int estimatedYear, Integer museumId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.finderId = finderId;
        this.dateFrom = dateFrom;
        this.estimatedYear = estimatedYear;
        this.museumId = museumId;
    }

    @Override
    public void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Finder ID: " + finderId);
        System.out.println("Date from: " + dateFrom);
        System.out.println("Estimated Year: " + estimatedYear);
        System.out.println("Museum ID: " + museumId);
    }
}
