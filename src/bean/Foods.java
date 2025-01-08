package bean;

import java.io.Serializable;

public class Foods implements Serializable {
    private int id;
    private String foodName;
    private String iconR;
    private String iconG;
    private Categories categories;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public String getIconR() {
        return iconR;
    }
    public void setIconR(String iconR) {
        this.iconR = iconR;
    }
    public String getIconG() {
        return iconG;
    }
    public void setIconG(String iconG) {
        this.iconG = iconG;
    }
    public Categories getCategories() {
        return categories;
    }
    public void setCategories(Categories categories) {
        this.categories = categories;
    }

}
