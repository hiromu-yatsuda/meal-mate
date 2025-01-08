package bean;

import java.io.Serializable;

public class RestFoods implements Serializable {
    private int id;
    private MemberAccounts memberAccounts;
    private Foods foods;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public MemberAccounts getMemberAccounts() {
        return memberAccounts;
    }
    public void setMemberAccounts(MemberAccounts memberAccounts) {
        this.memberAccounts = memberAccounts;
    }
    public Foods getFoods() {
        return foods;
    }
    public void setFoods(Foods foods) {
        this.foods = foods;
    }

}
