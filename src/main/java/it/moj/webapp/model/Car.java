package it.moj.webapp.model;


public class Car {

	private Integer id;
	private String model;
	private String make;
	private String preview;
	private String imagePath;
	private Integer price;
	//private static int visit = 0;
	
	public Car(){
	}
	
	public Car(Integer id, String model, String make, String preview,  String imagePath, Integer price){
		this.id = id;
		this.model = model;
		this.make = make;
		this.preview = preview;
		this.imagePath = imagePath;
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String image) {
		this.imagePath = image;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

/*	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}*/

/*	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Car))
			return false;
		Car other = (Car) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}*/

}