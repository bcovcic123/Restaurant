package GUI.src;


public class OrderItem {
	private MenuItem mi;
	private int amount;
	
	public MenuItem getMi() {
		return mi;
	}
	public void setMi(MenuItem mi) {
		this.mi = mi;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public OrderItem(MenuItem m, int a)
	{
		setMi(m);
		setAmount(a);
	}
	
	public String toString()
	{
		return this.getMi()+"  amount: "+this.getAmount();
	}

}
