package cz.uhk.restaurace.forms;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import cz.uhk.restaurace.model.IngredientGeneral.IngredientType;

public class NewIngredientForm {
	
	@Min(value = 0, message = "{Min.newIngredientForm.kcal}")
	private int kcal;
	@Min(value = 0, message = "{Min.newIngredientForm.fatGrams}")
	private int fatGrams;
	@Min(value = 0, message = "{Min.newIngredientForm.saccharideGrams}")
	private int saccharideGrams;
	@Min(value = 0, message = "{Min.newIngredientForm.proteinGrams}")
	private int proteinGrams;
	@NotNull
	private IngredientType type;
	@NotNull
	@Min(value = 0, message = "{Min.newIngredientForm.pricePerHundredGrams}")
	private BigDecimal pricePerHundredGrams;
	@NotEmpty(message = "{NotEmpty.newIngredientForm.enName}")
	private String enName;
	@NotEmpty(message = "{NotEmpty.newIngredientForm.csName}")
	private String csName;
	
	public NewIngredientForm() {
		pricePerHundredGrams = new BigDecimal(0);
	}

	public int getKcal() {
		return kcal;
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

	public BigDecimal getPricePerHundredGrams() {
		return pricePerHundredGrams;
	}

	public void setPricePerHundredGrams(BigDecimal pricePerHundredGrams) {
		this.pricePerHundredGrams = pricePerHundredGrams;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCsName() {
		return csName;
	}

	public void setCsName(String csName) {
		this.csName = csName;
	}
	
	
	

}
