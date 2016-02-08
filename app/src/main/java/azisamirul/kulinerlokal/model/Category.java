package azisamirul.kulinerlokal.model;

/**
 * Created by azisamirul on 28/09/2015.
 */
public class Category {

    private int id;
    private String category_name;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;

    public int GetId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getCategoryName(){
        return category_name;
    }
    public void setCategoryName(String category_name){
        this.category_name=category_name;
    }
}
