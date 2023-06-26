package RecipeKit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Recipe implements Comparable<Recipe>{
    private int occurrenceCount,lastUsedInWeekNumber;

    private String id;
    private String name;
    private List<String> steps;
    private List<Ingredient> ingredients;

    public Recipe() {
        this.id = UUID.randomUUID().toString();
        this.steps = new ArrayList<>();
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<String> getSteps() {
        return steps;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getOccurrenceCount() {
        return occurrenceCount;
    }

    public void setOccurrenceCount(int occurrenceCount) {
        this.occurrenceCount = occurrenceCount;
    }

    public int getLastUsedInWeekNumber() {
        return lastUsedInWeekNumber;
    }

    public void setLastUsedInWeekNumber(int lastUsedInWeekNumber) {
        this.lastUsedInWeekNumber = lastUsedInWeekNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return  "-------------------------------------------------------------------------------\n"
                +"Recipe Name: " + name + '\n' +
                "Steps:-\n" + stringArrayToString(steps) +
                "Ingredients:-\n" + arrayToString(ingredients)+
                "-------------------------------------------------------------------------------\n";
    }
    private String stringArrayToString(List<String> list){
        StringBuilder finalString = new StringBuilder();
        int i = 1;
        for (String item:list){
            finalString.append("\t").append(i).append(". ").append(item).append("\n");
            i++;
        }
        return finalString.toString();
    }

    private String arrayToString(List<? extends Ingredient> list){
        StringBuilder finalString = new StringBuilder();
        int i = 1;
        for (Ingredient item:list){
            finalString.append("\t").append(i).append(". ").append(item.toString()).append("\n");
            i++;
        }
        return finalString.toString();
    }


    public void deleteIngredient(int index){
        ingredients.remove(index);
    }

    public void deleteStep(int index){
        steps.remove(index);
    }

    @Override
    public int compareTo(Recipe recipe) {
        return getOccurrenceCount()-recipe.getOccurrenceCount();
    }
}
