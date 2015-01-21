package cz.uhk.restaurace.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dish_general")
public class DishGeneral {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private transient int amount = 1;
	@Column(precision = 15, scale = 2)
	private BigDecimal price;
	private int fatGrams;
	private int kcal;
	private int saccharideGrams;
	private int proteinGrams;
	@Enumerated(EnumType.STRING)
	private DishCategory dishCategory;
	@JsonIgnore
	private transient Map<Integer, IngredientGeneral> ingredients = new HashMap<Integer, IngredientGeneral>();
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pk.dish")
	private Set<DishIngredient> dishIngredients = new HashSet<DishIngredient>();
	@JsonIgnore
	private transient Collection<CustomerOrder> customerOrders;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pk.dish")
	@JsonIgnore
	private Set<OrderDish> ordersToPersist = new HashSet<OrderDish>();
	private transient DishLoc dishLoc;
	private transient Map<Integer, Integer>	amountsInOrders = new HashMap<Integer, Integer>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "dish")
	private Set<Comment> comments;

	public DishGeneral() {}

	public DishGeneral(BigDecimal price, int fatGrams, int kcal,
				int saccharideGrams, int proteinGrams, DishCategory dishCategory) {
		this.price = price;
		this.fatGrams = fatGrams;
		this.kcal = kcal;
		this.saccharideGrams = saccharideGrams;
		this.proteinGrams = proteinGrams;
		this.dishCategory = dishCategory;
	}

	public enum DishCategory{
		MAIN("dish", "hlavní jídlo"), DRINK("drink", "nápoj"), APPETIZER("appetizer", "pøedkrm"),
		DESSERT("dessert", "dezert"), TEPPANYAKI("teppanyaki", "teppanyaki");

		DishCategory(String name, String nameCs) {
			this.name = name;
			this.nameCs = nameCs;
		}

		private String name;
		private String nameCs;

		public String getName() {
			return name;
		}
		
		public String getNameCs() {
			return nameCs;
		}
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getFatGrams() {
		return fatGrams;
	}
	public void setFatGrams(int fatGrams) {
		this.fatGrams = fatGrams;
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
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
	public DishCategory getDishCategory() {
		return dishCategory;
	}
	public void setDishCategory(DishCategory dishCategory) {
		this.dishCategory = dishCategory;
	}
	public Collection<CustomerOrder> getCustomerOrders() {
		return customerOrders;
	}
	public void setCustomerOrders(Collection<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Map<Integer, IngredientGeneral> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Map<Integer, IngredientGeneral> ingredients) {
		this.ingredients = ingredients;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public DishLoc getDishLoc() {
		return dishLoc;
	}
	public void setDishLoc(DishLoc dishLoc) {
		this.dishLoc = dishLoc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<DishIngredient> getDishIngredients() {
		return dishIngredients;
	}
	public void setDishIngredients(Set<DishIngredient> dishIngredients) {
		this.dishIngredients = dishIngredients;
	}
	public void setAmountsInOrders(Map<Integer, Integer> amountsInOrders) {
		this.amountsInOrders = amountsInOrders;
	}
	public Map<Integer, Integer> getAmountsInOrders() {
		return amountsInOrders;
	}


	public BigDecimal getCustomDishPrice(){
		BigDecimal total = new BigDecimal("0.00");
		for(Map.Entry<Integer, IngredientGeneral> entry : this.getIngredients().entrySet()){
			if(entry.getValue() != null) {
				total = total.add(new BigDecimal(entry.getValue().getGrams()).multiply(entry.getValue().getPricePerHundredGrams())
						.divide(new BigDecimal(100)));
			}
		}
		return total;
	}

	public int getDishesTotalKcals(){
		Double kcal = 0d;
		for(Map.Entry<Integer, IngredientGeneral> ingr : this.ingredients.entrySet()){
			if(ingr != null && ingr.getValue() != null) {
				kcal += ingr.getValue().getKcal();
			}
		}
		return kcal.intValue();
	}
}
