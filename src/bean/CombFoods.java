package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CombFoods implements Serializable {
    private int id;
    private String name;
    private List<String> foods = new ArrayList<String>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(String food) {
        this.foods.add(food);
    }
}
