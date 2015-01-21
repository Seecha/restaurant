package cz.uhk.restaurace.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IngredientLoc implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	@Id
	private String language;
	private String name;
	
	public IngredientLoc() {
		
	}
	public IngredientLoc(int id, String language, String name) {
		this.id = id;
		this.language = language;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o ){
		return((o instanceof IngredientLoc) && (id == ((IngredientLoc) o).getId()) &&
				(language == ((IngredientLoc) o).getLanguage()) );	
	}
	@Override
	public int hashCode(){
		return id+language.hashCode();
	}
	
	

}
