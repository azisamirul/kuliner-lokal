package azisamirul.kulinerlokal.model;

/**
 * Created by azisamirul on 29/09/2015.
 */
public class UserTips {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String text;
    private String photo_url;
    private String firstName;
}
