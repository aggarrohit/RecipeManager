package Data;

import RecipeKit.Recipe;
import Utilities.SampleRecipes;

import java.util.ArrayList;
import java.util.List;

public class RecipePool {
    private static List<Recipe> recipeList;

    public RecipePool() {
        recipeList = new ArrayList<>();
        setSampleRecipes();
    }

    public static void replaceRecipe(Recipe recipe, int recipeIndex){
        recipeList.set(recipeIndex,recipe);
    }

    public static void addRecipeToPool(Recipe recipe){
        recipeList.add(recipe);
    }

    public static Recipe getRecipeById(String value) {
        return recipeList.stream()
                .filter(item -> value.equals(item.getId()))
                .findAny()
                .orElse(null);
    }

    public static Recipe getRecipeByIndex(int recipeIndex) {
        return  recipeList.get(recipeIndex);
    }

    private void setSampleRecipes(){

        SampleRecipes sampleRecipes = new SampleRecipes();
        recipeList =  sampleRecipes.getSampleRecipes();

    }

    public static List<Recipe> getRecipeList() {
        return recipeList;
    }

    public static void setRecipeList(List<Recipe> recipeList) {
        RecipePool.recipeList = recipeList;
    }

    public static String printRecipeNames() {
        return "Recipe Pool\n" +
                stringNamesFromArray(recipeList) ;
    }

    private static String stringNamesFromArray(List<? extends Recipe> list){
        StringBuilder finalString = new StringBuilder();
        int i = 1;
        for (Recipe item:list){
            finalString.append("[").append(i).append("] ").append(item.getName()).append("\n");
            i++;
        }
        return finalString.toString();
    }

}
