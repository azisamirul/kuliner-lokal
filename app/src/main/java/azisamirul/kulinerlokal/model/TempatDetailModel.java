package azisamirul.kulinerlokal.model;

/**
 * Created by azisamirul on 29/09/2015.
 */
public class TempatDetailModel {
private String id;
    private String name;
    private String phone;
    private String address;
    private String crossStreet;
    private double lat;
    private double lng;
    private String city;

    private int checkIn;

    double rating;


    public UserTips[] getUserTipses() {
        return userTipses;
    }

    public void setUserTipses(UserTips[] userTipses) {
        this.userTipses = userTipses;
    }

    private UserTips[] userTipses;
    private VenuePhotoModel[] venuePhotoModels;


    private String open;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VenuePhotoModel[] getVenuePhotoModels() {
        return venuePhotoModels;
    }

    public void setVenuePhotoModels(VenuePhotoModel[] venuePhotoModels) {
        this.venuePhotoModels = venuePhotoModels;
    }
}
