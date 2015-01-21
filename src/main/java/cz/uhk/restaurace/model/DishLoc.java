package cz.uhk.restaurace.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DishLoc implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	@Id
	private String language;
	private String name;
	private String description;
	
	public DishLoc() {
	}
	
	public DishLoc(int id, String language, String name, String description) {
		this.id = id;
		this.language = language;
		this.name = name;
		this.description = description;
		
	}
	
	public int getId() {
		return id;
	}
	public String getLanguage() {
		return language;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean equals(Object o ){
		return((o instanceof DishLoc) && (id == ((DishLoc) o).getId()) &&
				(language == ((DishLoc) o).getLanguage()) );	
	}
	@Override
	public int hashCode(){
		return id+language.hashCode();
	}

}
