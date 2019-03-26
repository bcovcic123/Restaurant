package GUI.src;


public class MenuItem {
	private Meal m;
	private double price;
	
	public MenuItem(Meal mm, double p)
	{
		setM(mm);
		setPrice(p);
	}
	
	public Meal getM() {
		return m;
	}
	public void setM(Meal m) {
		this.m = m;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString()
	{
		return getM().getName()+" "+getPrice();
	}

}
