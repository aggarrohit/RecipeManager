package Utilities;

import RecipeKit.Ingredient;
import RecipeKit.Recipe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SampleRecipes {

    List<Recipe> recipeList =new ArrayList<>();
    String filename = System.getProperty("user.dir") + "/assets/sample_recipes.txt";
    Recipe recipe;

    public List<Recipe> getSampleRecipes(){

        try {

            recipe = new Recipe();

            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;

            String ingredientsHeading = "ingredients-";
            String stepsHeading = "steps-";

            String nextDataType = "empty";

            while ((line = reader.readLine()) != null) {

                if(line.isEmpty()){
                    nextDataType = "empty";
                    recipeList.add(recipe);
                }else
                if(line.equals(ingredientsHeading)){
                    nextDataType = ingredientsHeading;
                }else
                if(line.equals(stepsHeading)){
                    nextDataType = stepsHeading;
                }else{
                    switch (nextDataType){
                        case "empty" -> readName(line);
                        case "ingredients-" -> readIngredient(line);
                        case "steps-" -> readStep(line);
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return recipeList;
    }

    private void readName(String line){
        recipe = new Recipe();
        recipe.setName(line);
    }

    private void readStep(String line){
        List<String> steps = recipe.getSteps();
        steps.add(line);
        recipe.setSteps(steps);
    }

    private void readIngredient(String line){
        List<Ingredient> ingredients = recipe.getIngredients();
        ingredients.add(shapeIngredient(line));
        recipe.setIngredients(ingredients);
    }

    private Ingredient shapeIngredient(String input){
        Pattern pattern = Pattern.compile("^([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)\\s*(l|pc|kg)\\s*(.*)$");

        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            float number = Float.parseFloat(matcher.group(1));
            String unit = matcher.group(2);
            String remainingText = matcher.group(3);

            return new Ingredient(remainingText,unit,number);

        }else {
            return new Ingredient("", "", 0);
        }
    }

}
