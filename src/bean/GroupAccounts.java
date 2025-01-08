package bean;

import java.io.Serializable;
import java.sql.Date;

public class GroupAccounts implements Serializable {
    private String id;
    private String name;
    private String email;
    private String password;
    private Date lastLogin;
    private Date passwordUpdated;
    private Groups groups;
    private boolean isAdmin;
    private String groupCode;

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
    public Groups getGroups() {
        return groups;
    }
    public void setGroups(Groups groups) {
        this.groups = groups;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

}
