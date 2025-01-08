package bean;

import java.io.Serializable;
import java.sql.Date;

public class MemberAccounts implements Serializable {
    private String id;
    private String name;
    private String email;
    private String password;
    private Date lastLogin;
    private Date passwordUpdated;
    private Languages languages;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    public Date getPasswordUpdated() {
        return passwordUpdated;
    }
    public void setPasswordUpdated(Date passwordUpdated) {
        this.passwordUpdated = passwordUpdated;
    }
    public Languages getLanguages() {
        return languages;
    }
    public void setLanguages(Languages languages) {
        this.languages = languages;
    }

}
