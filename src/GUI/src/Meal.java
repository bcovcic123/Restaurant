package GUI.src;

import java.util.ArrayList;
import java.util.List;

public class Meal {
	
	private String name;
	private String type;
	private ArrayList<String> ingredients;
	
	public Meal(String n, String t, ArrayList<String> l)
	{
		setName(n);
		setType(t);
		setIngredients(l);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	


}
