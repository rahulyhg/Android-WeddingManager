package com.sim.weddingmanager.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sim.weddingmanager.entities.Item;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;


public class BaseFeedParser {
	// names of the XML tags
	static final String ROOT = "root";
	
	static final String HOTELS = "hotels";
	static final String HOTEL = "hotel";
	
	static final String CARS = "cars";
	static final String CAR = "car";
	
	static final String DECOS = "decos";
	static final String DECO = "deco";
	
	static final String CAKES = "cakes";
	static final String CAKE = "cake";
	
	static final String NAME = "name";
	static final String PRICE = "price";
	static final String PICTURE = "picture";
    private InputStream inputStream;
	

	public BaseFeedParser() {
		
	}


	public List<Item> parse() {

		final Item current = new Item();
		RootElement root = new RootElement(ROOT);
		
		final List<Item> items = new ArrayList<Item>();
		
		Element hotelsElement = root.getChild(HOTELS);
		Element hotelElement = hotelsElement.getChild(HOTEL);
		
		hotelElement.setEndElementListener(new EndElementListener() {
			public void end() {
				items.add(current.copy());
			}
		});

		hotelElement.getChild(NAME).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String name) {
						current.setName(name);
						current.setCategory("Hotel");
					}
				});

		hotelElement.getChild(PRICE).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String price) {
						current.setPrice(price);
					}
				});
		hotelElement.getChild(PICTURE).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String picture) {
						current.setPicture(picture);
					}
				});
		

		Element carsElement = root.getChild(CARS);
		Element carElement = carsElement.getChild(CAR);
		
		carElement.setEndElementListener(new EndElementListener() {
			public void end() {
				items.add(current.copy());
			}
		});

		carElement.getChild(NAME).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String name) {
						current.setName(name);
						current.setCategory("Car");
					}
				});

		carElement.getChild(PRICE).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String price) {
						current.setPrice(price);
					}
				});
		carElement.getChild(PICTURE).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String picture) {
						current.setPicture(picture);
					}
				});
		
		
		Element cakesElement = root.getChild(CAKES);
		Element cakeElement = cakesElement.getChild(CAKE);
		
		cakeElement.setEndElementListener(new EndElementListener() {
			public void end() {
				items.add(current.copy());
			}
		});

		cakeElement.getChild(NAME).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String name) {
						current.setName(name);
						current.setCategory("Cake");
					}
				});

		cakeElement.getChild(PRICE).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String price) {
						current.setPrice(price);
					}
				});
		cakeElement.getChild(PICTURE).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String picture) {
						current.setPicture(picture);
					}
				});
		Element decosElement = root.getChild(DECOS);
		Element decoElement = decosElement.getChild(DECO);
		
		decoElement.setEndElementListener(new EndElementListener() {
			public void end() {
				items.add(current.copy());
			}
		});

		decoElement.getChild(NAME).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String name) {
						current.setName(name);
						current.setCategory("Deco");
					}
				});

		decoElement.getChild(PRICE).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String price) {
						current.setPrice(price);
					}
				});
		decoElement.getChild(PICTURE).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String picture) {
						current.setPicture(picture);
					}
				});
		
		try {
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8,
					root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return items;
	}


	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
