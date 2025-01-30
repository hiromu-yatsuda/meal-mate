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

//    従業員一覧用
    private Date last_login;
    private boolean is_admin;

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
    public void setLastLogin(Date last_login) {
        this.lastLogin = last_login;
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
	public Date getLast_login() {
		return last_login;
	}
	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}
	public boolean isIs_admin() {
		return is_admin;
	}
	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

}
