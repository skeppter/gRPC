// Datei: src/main/java/tradearea/warehouse/WarehouseSimulation.java
package simulation;

import model.WarehouseData;
import model.Product;
import hello.Hello.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseSimulation {

	private double getRandomDouble(int inMinimum, int inMaximum) {
		double number = (Math.random() * ((inMaximum - inMinimum) + 1)) + inMinimum;
		return Math.round(number * 100.0) / 100.0;
	}

	private int getRandomInt(int inMinimum, int inMaximum) {
		double number = (Math.random() * ((inMaximum - inMinimum) + 1)) + inMinimum;
		return (int) Math.round(number);
	}

	public WarehouseData getData(String inID) {
		WarehouseData data = new WarehouseData();
		data.setWarehouseID(inID);
		data.setWarehouseName("Linz Bahnhof");

		List<Product> products = generateProducts();
		data.setProducts(products);

		return data;
	}

	private List<Product> generateProducts() {
		List<Product> products = new ArrayList<>();
		products.add(new Product("00-443175", "Bio Orangensaft Sonne", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443176", "Bio Apfelsaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443177", "Bio Traubensaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443178", "Bio Karottensaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443179", "Bio Tomatensaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443180", "Bio Kirschsaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443181", "Bio Ananassaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443182", "Bio Rote Bete Saft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443183", "Bio Mango Saft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443184", "Bio Birnensaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443185", "Bio Cranberrysaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443186", "Bio Zitronensaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443187", "Bio Limettensaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443188", "Bio Grapefruitsaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443189", "Bio Erdbeersaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443190", "Bio Himbeersaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443191", "Bio Johannisbeersaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443192", "Bio Blaubeersaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443193", "Bio Granatapfelsaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443194", "Bio Melonensaft", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));
		products.add(new Product("00-443195", "Bio Kokosnusswasser", "Getränk", getRandomInt(2000, 3000), "Packung 1L"));

		return products;
	}

}
