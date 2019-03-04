package ie.worldconquest.models;

import java.io.Serializable;
import java.util.UUID;

public class Trip implements Serializable {
    public String tripId;
    public String tripName;
    public String country;
    public boolean milestone1;
    public boolean milestone2;
    public boolean milestone3;
    public boolean currentTrip;

    public Trip () {}

    public Trip(String name, String country, boolean milestone1, boolean milestone2, boolean milestone3, boolean currentTrip)
    {
        this.tripId = UUID.randomUUID().toString();
        this.tripName = name;
        this.country = country;
        this.milestone1 = milestone1;
        this.milestone2 = milestone2;
        this.milestone3 = milestone3;
        this.currentTrip = currentTrip;
    }

    @Override
    public String toString()
    {
        return tripId + " " + tripName + ", " + country + ", " + milestone1 + ", " + milestone2 + ", " + milestone3 + ", " + currentTrip;
    }
}
