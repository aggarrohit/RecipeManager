package RecipeKit;

import Menus.DietitianMenuController;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class RecipeController {

    protected Recipe recipe;
    protected DietitianMenuController dietitianMenuController;
    protected RecipeView recipeView;

    protected RecipeModel recipeModel;
    protected int recipeIndex;
    protected String[] allowedMeasurements = new String[]{"pc","l","kg"};
    public RecipeController(DietitianMenuController dietitianMenuController) {
        this.dietitianMenuController=dietitianMenuController;
        recipeView = new RecipeView();
        recipeModel = new RecipeModel();
    }


    void askRecipeName(){
        recipeView.askRecipeName();
        recipe.setName(requestInput());
        showOptionsAfterAddingName();
    }

    protected void askSteps(){
        recipeView.printThis("Please add step "+(recipe.getSteps().size()+1));
        String step = requestInput();

        List<String> stepsList = recipe.getSteps();
        stepsList.add(step);
        recipe.setSteps(stepsList);

        showOptionsAfterAddingStep();
    }

    abstract void showOptionsAfterAddingStep();
    abstract void handleOptionsAfterAddingStep();

    abstract void showOptionsAfterAddingName();

    protected void askForIngredients(){
        recipeView.printThis("Please add ingredient "+(recipe.getIngredients().size()+1));
        recipeView.printThis("Name");
        String name = requestInput();
        askIngredientMeasurement(name);
    }


    private void askIngredientMeasurement(String name) {
        recipeView.printThis("Measurement (pc, l or kg)");
        String measurement = requestInput();

        try {
            if(Arrays.asList(allowedMeasurements).contains(measurement)){
                askIngredientAmount(name, measurement);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            recipeView.showInvalidInput();
            askIngredientMeasurement(name);
        }
    }

    private void askIngredientAmount(String name, String measurement) {
        recipeView.printThis("Amount");
        float amount;
        try {
            amount = Float.parseFloat(requestInput());
            List<Ingredient> ingredientList = recipe.getIngredients();
            ingredientList.add(new Ingredient(name, measurement,amount));
            recipe.setIngredients(ingredientList);

            showOptionsAfterAddingIngredient();

        }catch (NumberFormatException e){
            recipeView.printThis("Invalid input\n");
            askIngredientAmount(name,measurement);
        }
    }

    abstract void showOptionsAfterAddingIngredient();

    abstract void handleOptionsAfterAddingIngredient() ;

    protected String requestInput(){
        try {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            if (input.isBlank() || input.isEmpty()) throw new Exception();
            return input;
        }catch (Exception e){
            recipeView.showInvalidInput();
            return requestInput();
        }

    }

    protected void requestNumberInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            int optionSelected = Integer.parseInt(input.trim());
            recipeModel.setSelectedOption(optionSelected);
        }catch (NumberFormatException | IndexOutOfBoundsException exception){
            recipeModel.setSelectedOption(-1);
        }
    }

    protected void showDietitianMenu(){
        dietitianMenuController.showDietitianMenu();
    }
}
