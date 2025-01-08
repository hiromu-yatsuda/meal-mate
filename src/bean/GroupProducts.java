package bean;

import java.io.Serializable;

public class GroupProducts implements Serializable {
    private int id;
    private Products products;
    private Groups group;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Products getProducts() {
        return products;
    }
    public void setProducts(Products products) {
        this.products = products;
    }
    public Groups getGroup() {
        return group;
    }
    public void setGroup(Groups group) {
        this.group = group;
    }

}
