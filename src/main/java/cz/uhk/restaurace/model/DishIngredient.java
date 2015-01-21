package cz.uhk.restaurace.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dann on 25.12.2014.
 */

@Entity
@Table(name = "ordered_dishes_ingredients")
@AssociationOverrides({
        @AssociationOverride(name = "pk.dish", joinColumns = @JoinColumn(name = "dish_id")),
        @AssociationOverride(name = "pk.ingredient", joinColumns = @JoinColumn(name = "ingredient_id"))})
public class DishIngredient{

    @EmbeddedId
    private DishIngredientId pk = new DishIngredientId();
    private int grams;

    public DishIngredient() {
    }

    public DishIngredient(DishGeneral dish, IngredientGeneral ingredient, int grams) {
        this.pk.dish = dish;
        this.pk.ingredient = ingredient;
        this.grams = grams;
    }

    public DishIngredientId getPk() {
        return pk;
    }

    public void setPk(DishIngredientId pk) {
        this.pk = pk;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public void setIngredient(IngredientGeneral ingredient){
        this.pk.setIngredient(ingredient);
    }

    @Transient
    public IngredientGeneral getIngredient(){
        return this.pk.getIngredient();
    }

    public void setDish(DishGeneral dish){
        this.pk.setDish(dish);
    }

    @Transient
    public DishGeneral getDish(){
        return this.pk.getDish();
    }

    @Embeddable
    public static class DishIngredientId implements Serializable{

        public static final long serialVersionUID = 1L;

        @ManyToOne
        private IngredientGeneral ingredient;
        @ManyToOne
        private DishGeneral dish;

        public IngredientGeneral getIngredient() {
            return ingredient;
        }

        public void setIngredient(IngredientGeneral ingredient) {
            this.ingredient = ingredient;
        }

        public DishGeneral getDish() {
            return dish;
        }

        public void setDish(DishGeneral dish) {
            this.dish = dish;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DishIngredientId that = (DishIngredientId) o;

            if (!dish.equals(that.dish)) return false;
            if (!ingredient.equals(that.ingredient)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = ingredient.hashCode();
            result = 31 * result + dish.hashCode();
            return result;
        }
    }


}
