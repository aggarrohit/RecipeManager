package RecipeKit;

public class Ingredient {
    private String name;
    private String measurement;
    private float amount;



    public Ingredient(String name, String measurement, float amount) {
        this.name = name;
        this.measurement = measurement;
        this.amount = amount;
    }



    @Override
    public String toString() {
        return  name + " - " + amount + " " + measurement;
    }


}
