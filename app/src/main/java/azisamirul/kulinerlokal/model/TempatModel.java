package azisamirul.kulinerlokal.model;

/**
 * Created by azisamirul on 29/09/2015.
 */
public class TempatModel {

    private String venues_id;
    private String venues_name;

    private double lat;
    private double lng;
    private int distance;
    private String address;
    private String crossStreet;
    private String city;

    private int checkIn;

    public String getVenues_id() {
        return venues_id;
    }

    public void setVenues_id(String venues_id) {
        this.venues_id = venues_id;
    }

    public String getVenues_name() {
        return venues_name;
    }

    public void setVenues_name(String venues_name) {
        this.venues_name = venues_name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }
}
