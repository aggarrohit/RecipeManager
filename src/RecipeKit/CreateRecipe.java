package RecipeKit;

import Data.RecipePool;
import Menus.DietitianMenuController;

public class CreateRecipe extends RecipeController{

    public CreateRecipe(DietitianMenuController dietitianMenuController) {
        super(dietitianMenuController);
        this.dietitianMenuController=dietitianMenuController;
        recipe = new Recipe();
        askRecipeName();
    }

    @Override
    void showOptionsAfterAddingStep(){
        recipeView.drawMenuOptions(new String[]{ "Add more steps",
                "Add ingredients",
                "Cancel creating recipe"
        });

        handleOptionsAfterAddingStep();
    }

    @Override
    void handleOptionsAfterAddingStep() {
        requestNumberInput();
        try {
            switch (recipeModel.getSelectedOption()) {
                case 1 -> askSteps();
                case 2 -> askForIngredients();
                case 3 -> {
                    recipeView.clearTerminal();
                    showDietitianMenu();
                }
                default -> throw new IndexOutOfBoundsException();
            }
        }catch (IndexOutOfBoundsException e){
            recipeView.showInvalidInput();
            handleOptionsAfterAddingStep();
        }
    }

    @Override
    void showOptionsAfterAddingName() {
        askSteps();
    }

    @Override
    void showOptionsAfterAddingIngredient(){
        recipeView.drawMenuOptions(new String[]{ "Add more ingredients",
                "Cancel creating recipe",
                "Save recipe"
        });
        requestNumberInput();
        handleOptionsAfterAddingIngredient();
    }

    @Override
    void handleOptionsAfterAddingIngredient() {
        try {
            switch (recipeModel.getSelectedOption()) {
                case 1 -> askForIngredients();
                case 2 -> dietitianMenuController.showDietitianMenu();
                case 3 -> {
                    recipeView.clearTerminal();
                    System.out.println("Recipe created!!");
                    System.out.println(recipe);
                    RecipePool.addRecipeToPool(recipe);
                    showDietitianMenu();
                }
                default -> throw new IndexOutOfBoundsException();
            }
        }catch (IndexOutOfBoundsException e){
            recipeView.showInvalidInput();
            handleOptionsAfterAddingIngredient();
        }
    }

}
