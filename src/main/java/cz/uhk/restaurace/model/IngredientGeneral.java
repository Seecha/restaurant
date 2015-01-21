package cz.uhk.restaurace.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;


@Table(name = "ingredient_general")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IngredientGeneral {

	@Id
	@GeneratedValue
	private int id;
	/**
	 * kcal per hundred grams
	 */
	private int kcal;
	private int fatGrams;
	private int saccharideGrams;
	private int proteinGrams;
	private transient int grams = 0;
	@Enumerated(EnumType.STRING)
	private IngredientType type;
	@JsonIgnore
	private transient Collection<DishGeneral> dishes;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pk.ingredient", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<DishIngredient> dishIngredients = new HashSet<DishIngredient>();
	@Column(name="price")
	private BigDecimal pricePerHundredGrams = new BigDecimal(0);
	private transient IngredientLoc ingredientLocalized;
	private transient Map<Integer, Integer> gramsInDishes = new HashMap<Integer, Integer>();
	
	public IngredientGeneral() {}

	public IngredientGeneral(int kcal, int fatGrams, int saccharideGrams,
					  int proteinGrams, IngredientType type, BigDecimal pricePerHundredGrams) {
		this.kcal = kcal;
		this.fatGrams = fatGrams;
		this.saccharideGrams = saccharideGrams;
		this.proteinGrams = proteinGrams;
		this.type = type;
		this.pricePerHundredGrams = pricePerHundredGrams;
	}

	public enum IngredientType{

		VEGETABLE("Zelenina", "Vegetable",  "vegetable"),
		MEAT("Maso", "Meat", "meat"),
		FRUIT("Ovoce", "Fruit", "fruit"),
		SPICE("Koreni", "Spice","spice");

		private String descriptionCs;
		private String descriptionEn;
		private String url;

		IngredientType(String descriptionCs, String descriptionEn,  String url) {
			this.descriptionCs = descriptionCs;
			this.descriptionEn = descriptionEn;
			this.url = url;
		}

		public String getUrl() {
			return url;
		}

		public String getDescriptionCs() {
			return descriptionCs;
		}
		
		public String getDescriptionEn() {
			return descriptionEn;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKcalPerHundredGrams() {
		return kcal;
	}

	public double getKcal(){
		return this.kcal * this.grams / 100;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public int getFatGrams() {
		return fatGrams;
	}

	public void setFatGrams(int fatGrams) {
		this.fatGrams = fatGrams;
	}

	public int getSaccharideGrams() {
		return saccharideGrams;
	}

	public void setSaccharideGrams(int saccharideGrams) {
		this.saccharideGrams = saccharideGrams;
	}

	public int getProteinGrams() {
		return proteinGrams;
	}

	public void setProteinGrams(int proteinGrams) {
		this.proteinGrams = proteinGrams;
	}

	public IngredientType getType() {
		return type;
	}

	public void setType(IngredientType type) {
		this.type = type;
	}

	public Collection<DishGeneral> getDishes() {
		return dishes;
	}

	public void setDishes(Collection<DishGeneral> dishes) {
		this.dishes = dishes;
	}

	public int getGrams() {
		return grams;
	}

	public void setGrams(int grams) {
		this.grams = grams;
	}

	public BigDecimal getPricePerHundredGrams() {
		return pricePerHundredGrams;
	}

	public void setPricePerHundredGrams(BigDecimal pricePerHundredGrams) {
		this.pricePerHundredGrams = pricePerHundredGrams;
	}

	public IngredientLoc getIngredientLocalized() {
		return ingredientLocalized;
	}

	public void setIngredientLocalized(IngredientLoc ingredientLocalized) {
		this.ingredientLocalized = ingredientLocalized;
	}

	public Set<DishIngredient> getDishIngredients() {
		return dishIngredients;
	}

	public void setDishIngredients(Set<DishIngredient> dishIngredients) {
		this.dishIngredients = dishIngredients;
	}
	public void setGramsInDishes(Map<Integer, Integer> gramsInDishes) {
		this.gramsInDishes = gramsInDishes;
	}
	public Map<Integer, Integer> getGramsInDishes() {
		return gramsInDishes;
	}
	
}
