package bean;

import java.io.Serializable;

public class ProductsList implements Serializable {

	private String pro_name;
	private String jancode;
	private String foods_id;
	private String foods_name;
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getJancode() {
		return jancode;
	}
	public void setJancode(String jan_code) {
		this.jancode = jan_code;
	}
	public String getFoods_id() {
		return foods_id;
	}
	public void setFoods_id(String foods_ids) {
		this.foods_id = foods_ids;
	}
	public String getFoods_name() {
		return foods_name;
	}
	public void setFoods_name(String food_names) {
		this.foods_name = food_names;
	}

}
