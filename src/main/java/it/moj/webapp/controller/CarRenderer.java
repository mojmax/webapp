package it.moj.webapp.controller;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import it.moj.webapp.model.Car;

public class CarRenderer implements ListitemRenderer<Car> {
	public void render ( final Listitem item, Car car, int index ) {
		item.appendChild(new Listcell(car.getModel()));
		item.appendChild(new Listcell(car.getMake()));
		item.appendChild(new Listcell(car.getPrice().toString()));
	}
}
