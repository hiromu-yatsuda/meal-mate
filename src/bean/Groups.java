package bean;

import java.io.Serializable;

public class Groups implements Serializable {
    private String groupCode;
    private String phoneNum;
    private String email;
    private String name;

    // デフォルトコンストラクタ
    public Groups() {
    }

    // パラメータ付きコンストラクタ
    public Groups(String groupCode, String phoneNum, String email, String name) {
        this.groupCode = groupCode;
        this.phoneNum = phoneNum;
        this.email = email;
        this.name = name;
    }

    public String getGroupCode() {
        return groupCode;
    }
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}