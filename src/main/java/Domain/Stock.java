package Domain;

public class Stock {
	int id;
	String manufacturer;
	String model;
	int quantity;
	int price;
	
	public Stock() {
		
	}
	
	public Stock(int id, String manufacturer, String model, int quantity, int price) {
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
    public String toString() {
        return "Stock{" + "id=" + id + ", manufacturer=" + manufacturer + ","
        		+ " model=" + model + ", quantity=" + quantity +  " price=" + price +'}';
    }
	
	
}
