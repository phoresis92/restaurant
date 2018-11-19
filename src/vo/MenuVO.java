package vo;

import java.util.ArrayList;

public class MenuVO {
	
	private int menu_id ;
	private String menu_name;
	private int price;
	private String comment;
	private int count;
	
	public MenuVO(int menu_id, String menu_name, int price, String comment) {
		super();
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.price = price;
		this.comment = comment;
		
	}
	
	
	@Override
	public String toString() {
		return menu_name + " 가격: " + price + " "+ count +"개"+ " = "+ price*count +"원";
	}



	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getComment() {
		return comment;
	}
	public void setConnent(String connent) {
		this.comment = connent;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	

}
