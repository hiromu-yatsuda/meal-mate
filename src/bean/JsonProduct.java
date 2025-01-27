package bean;

import java.io.Serializable;

public class JsonProduct implements Serializable {
    private boolean isJan;
    private String name;
    private String jan;
    private String[] checkedItems;
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
    public String[] getCheckedItems() {
        return checkedItems;
    }
    public void setCheckedItems(String[] checkedItems) {
        this.checkedItems = checkedItems;
    }


}
