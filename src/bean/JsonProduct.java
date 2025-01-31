package bean;

import java.io.Serializable;

public class JsonProduct implements Serializable {
    private boolean isJan;
    private String name;
    private String jan;
    private String[] foodName;
    private String[] checkedItemsId;
    public boolean isJan() {
        return isJan;
    }
    public void setIsJan(boolean isJan) {
        this.isJan = isJan;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getJan() {
        return jan;
    }
    public void setJan(String jan) {
        this.jan = jan;
    }
    public String[] getFoodName() {
        return foodName;
    }
    public void setFoodName(String[] foodName) {
        this.foodName = foodName;
    }
    public String[] getCheckedItemsId() {
        return checkedItemsId;
    }
    public void setCheckedItems(String[] checkedItemsId) {
        this.checkedItemsId = checkedItemsId;
    }


}
