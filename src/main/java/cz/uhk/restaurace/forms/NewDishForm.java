package cz.uhk.restaurace.forms;

import java.math.BigDecimal;







import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import cz.uhk.restaurace.model.DishGeneral.DishCategory;

public class NewDishForm {
	@NotNull
	@Min(value = 0, message = "{Min.newDishForm.price}")
	private BigDecimal price;
	@Min(value = 0, message = "{Min.newDishForm.fatGrams}")
	private int fatGrams;
	@Min(value = 0, message = "{Min.newDishForm.kcal}")
	private int kcal;
	@Min(value = 0, message = "{Min.newDishForm.saccharideGrams}")
	private int saccharideGrams;
	@Min(value = 0, message = "{Min.newDishForm.proteinGrams}")
	private int proteinGrams;
	@NotNull
	private DishCategory dishCategory;
	@NotEmpty(message = "{NotEmpty.newDishForm.csName}")
	private String csName;
	@NotEmpty(message = "{NotEmptynewDishForm.csDescription}")
	private String csDescription;
	@NotEmpty(message = "{NotEmptynewDishForm.enName}")
	private String enName;
	@NotEmpty(message = "{NotEmptynewDishForm.enDescription}")
	private String enDescription;
	
	public NewDishForm() {
		price = new BigDecimal(0);
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

	public String getCsName() {
		return csName;
	}

	public void setCsName(String csName) {
		this.csName = csName;
	}

	public String getCsDescription() {
		return csDescription;
	}

	public void setCsDescription(String csDescription) {
		this.csDescription = csDescription;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getEnDescription() {
		return enDescription;
	}

	public void setEnDescription(String enDescription) {
		this.enDescription = enDescription;
	}
	
	

}
