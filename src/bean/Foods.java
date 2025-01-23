package bean;

import java.io.Serializable;

public class Foods implements Serializable {
    private int id;
    private String foodName;
    private String icon;
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
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Categories getCategories() {
        return categories;
    }
    public void setCategories(Categories categories) {
        this.categories = categories;
    }

}
