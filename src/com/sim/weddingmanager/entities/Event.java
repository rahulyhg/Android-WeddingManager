package com.sim.weddingmanager.entities;

import garin.artemiy.sqlitesimple.library.annotations.Column;
import garin.artemiy.sqlitesimple.library.util.ColumnType;

public class Event {
	
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_BUDGET = "budget";
	public static final String COLUMN_CATEGORY = "category";
	
	@Column(name = COLUMN_ID, type = ColumnType.INTEGER, isPrimaryKey = true, isAutoincrement = true)
	private int id;
	
	@Column(name = COLUMN_NAME)
	private String name;
	
	@Column(name = COLUMN_CATEGORY)
	private String category;
	
	@Column(name = COLUMN_BUDGET)
	private double budget;

	/**
	 * 
	 */
	public Event() {
	}

	/**
	 * @param id
	 * @param name
	 * @param budget
	 */
	public Event(int id, String name,String category, double budget) {
		super();
		this.id = id;
		this.name = name;
		this.budget = budget;
		this.category = category;
	}

	/**
	 * @param name
	 * @param type
	 * @param budget
	 */
	public Event(String name,String category, double budget) {
		super();
		this.name = name;
		this.budget = budget;
		this.category = category;
	}

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

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
