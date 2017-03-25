package it.moj.webapp.controller;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;

import it.moj.webapp.model.Car;
import it.moj.webapp.model.CarService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SearchControllerRenderer extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	@Wire
	private Textbox keywordBox;
	
	@Wire
	private Listbox carListbox;
	
	@Wire
	private Label keywordBoxSearched;
	
	@Wire 
	private Image previewImage;
	
	@Wire
	private Label modelLabel;
	
	@Wire
	private Label makeLabel;
	
	@Wire
	private Label priceLabel;
	
	@Wire 
	private Label descriptionLabel;
	
	@Wire
	private Label visitLabel;
	
	// wire service carService
	@WireVariable("carServiceSpring")
	CarService carService;
	
	//CarService carService = new CarServiceImpl();
	
	
	
//	@Override
	public void doAfterCompose(Component comp) throws Exception {		
	 	super.doAfterCompose(comp);
	 	List<Component> l =  comp.getChildren();
		l = null;
		List<Car> allCar = carService.findAll();
		carListbox.setModel(new ListModelList<Car>(allCar));
		
		/*List<Car> result = carService.findAll();
		carListbox.setModel(new ListModelList<Car>(result));
		ListitemRenderer<Car> lsRend = new CarRenderer();
		carListbox.setItemRenderer(lsRend);*/
		//cleanDetail();
		
	};
	
		
	@Listen("onClick=#searchButton; onOK=#keywordBox")					   
	public void search() {
		String key = keywordBox.getValue();
		/*List<Car> result = new ListModelList<Car>(carService.search(key));		
		carListbox.setModel((ListModelList<Car>) result);
		ListitemRenderer<Car> lsRend = new CarRenderer();		
		carListbox.setItemRenderer(lsRend);*/		
		List<Car> result = carService.search(key);
		carListbox.setModel (new ListModelList<Car>(result));
		System.out.println("Search() " );
		System.out.println("carListbox :" + carListbox);
		System.out.println("Set Model " +  result);
		
		visitLabel.setValue((new Integer(carService.getVisited())).toString());
		if ( result.isEmpty()) {
			keywordBoxSearched.setVisible(false);
			cleanDetail();
		}
		else {
			keywordBoxSearched.setVisible(true);
			if (key.equals("") ) { 
				keywordBoxSearched.setValue("Founded :  All row Returned");
			} else {
				keywordBoxSearched.setValue("Founded : " + key);
			}
			System.out.println("Show 1 occ " + result.getClass() + " :" + result.get(0));
			System.out.println("carlistBox " + carListbox.getClass() + " :" + carListbox);
			showDetail(result.get(0));			
		}
	}
	
	@Listen("onSelect=#carListbox")
	public void showDetail() {
		//???
		System.out.println("entro in showDetail()" );
		System.out.println("carlistBox " + carListbox.getClass() + " :" + carListbox);
		System.out.println("carListbox.getSelectedItem() " + carListbox.getSelectedItem().getClass() + " : " + carListbox.getSelectedItem());
		System.out.println("carListbox.getModel()" + carListbox.getModel().getClass() + " : "+ carListbox.getModel());
		Car selected = (Car) carListbox.getModel().getElementAt(carListbox.getSelectedIndex()); 
		Car lc1 = carListbox.getSelectedItem().getValue();
		System.out.println("carListbox.getSelectedItem() " + carListbox.getSelectedItem().getClass() + " : " + carListbox.getSelectedItem() );
		System.out.println("carListbox.getSelectedItem().getValue() " + carListbox.getSelectedItem().getValue());
		previewImage.setSrc(selected.getImagePath());
		System.out.println(selected.getImagePath());
		modelLabel.setValue(selected.getModel());
		makeLabel.setValue(selected.getMake());
		priceLabel.setValue(selected.getPrice().toString());
		descriptionLabel.setValue(selected.getPreview());
		System.out.println("Visited >>: " + carService.getVisited());
		visitLabel.setValue((new Integer(carService.getVisited())).toString());
		setDetailVisibility(true);
		System.out.println("esco da showDetail()" );
	}
	
	public void showDetail(Car car) {
		System.out.println("entro in showDetail(Car car) : " + car.getClass() + " : " + car);
		previewImage.setSrc(car.getImagePath());
		modelLabel.setValue(car.getModel());
		makeLabel.setValue(car.getMake());
		priceLabel.setValue(car.getPrice().toString());
		descriptionLabel.setValue(car.getPreview());
		setDetailVisibility(true);	
		System.out.println("esco da showDetail(Car car) : ");
	}	
	
	public void cleanDetail(){
		previewImage.setSrc("");
		modelLabel.setValue("");
		makeLabel.setValue("");
		priceLabel.setValue("");
		descriptionLabel.setValue("");
		setDetailVisibility(false);		
	};
	private void setDetailVisibility(boolean visibility) {
		makeLabel.setVisible(visibility);
		modelLabel.setVisible(visibility);
		previewImage.setVisible(visibility);
		priceLabel.setVisible(visibility);
		descriptionLabel.setVisible(visibility);
	}

}
