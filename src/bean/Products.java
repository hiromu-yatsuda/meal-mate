package bean;

import java.io.Serializable;

public class Products implements Serializable {
    private String janCode;
    private String name;
    private boolean isCommon;

    public String getJanCode() {
        return janCode;
    }
    public void setJanCode(String janCode) {
        this.janCode = janCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isCommon() {
        return isCommon;
    }
    public void setCommon(boolean isCommon) {
        this.isCommon = isCommon;
    }
}
