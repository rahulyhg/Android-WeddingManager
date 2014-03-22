package com.sim.weddingmanager.entities;



public class Item {
	private String name;
	private String price;
	private String picture;
	private String category;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public Item copy(){
		Item copy = new Item();
		copy.name = name;
		copy.price = price;
		copy.category = category;
		copy.picture = picture;
		return copy;
	}
	
	

}
