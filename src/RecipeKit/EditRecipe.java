package RecipeKit;

import Data.RecipePool;
import Menus.DietitianMenuController;
import Roles.Dietitian;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EditRecipe extends RecipeController{

    private String[] editOptions(){
        return new String[]{
                "Edit recipe name",
                "Add new step",
                "Add new ingredient",
                "Delete step",
                "Delete ingredient",
                "Go to dietitian menu"
        };
    }
    public EditRecipe(DietitianMenuController dietitianMenuController, int recipeIndex) {
        super(dietitianMenuController);
        this.dietitianMenuController=dietitianMenuController;
        this.recipe=RecipePool.getRecipeByIndex(recipeIndex);
        this.recipeIndex=recipeIndex;

        showEditOptions();
    }

    private void showEditOptions(){
        recipeView.drawMenuOptions(editOptions());
        requestNumberInput();
        try {
            proceedWithEditOption();
        }catch (IndexOutOfBoundsException e){
            recipeView.showInvalidInput();
            showEditOptions();
        }
    }

    private void proceedWithEditOption(){
        switch (recipeModel.getSelectedOption()) {
            case 1 -> askRecipeName();
            case 2 -> askSteps();
            case 3 -> askForIngredients();
            case 4 -> {
                deleteStep();
                askSaveOrContinue();
            }
            case 5 -> {
                deleteIngredient();
                askSaveOrContinue();
            }
            case 6 -> showDietitianMenu();
            default -> throw new IndexOutOfBoundsException();
        }

    }

    private void deleteStep(){
        recipeView.askStepNumberToDelete();
        requestNumberInput();

        recipe.deleteStep(recipeModel.getSelectedOption()-1);
    }

    private void deleteIngredient(){
        recipeView.askIngredientNumberToDelete();
        requestNumberInput();

        recipe.deleteIngredient(recipeModel.getSelectedOption()-1);
    }

    private void showOptionsDuringEditing(){
        recipeView.drawMenuOptions(new String[]{ "Save recipe",
                "Continue editing"
        });
        requestNumberInput();
    }
    private void askSaveOrContinue(){
        showOptionsDuringEditing();
        handleRecipeEditOption();
    }

    private void handleRecipeEditOption() {
        try {
            switch (recipeModel.getSelectedOption()) {
                case 1 -> {
                    RecipePool.replaceRecipe(recipe, recipeIndex);
                    showDietitianMenu();
                }
                case 2 -> showEditOptions();
                default -> throw new IndexOutOfBoundsException();
            }
        }catch (IndexOutOfBoundsException e){
            recipeView.showInvalidInput();
            handleRecipeEditOption();
        }
    }


    @Override
    void showOptionsAfterAddingStep() {
       showOptionsDuringEditing();
    }

    @Override
    void handleOptionsAfterAddingStep() {
        handleRecipeEditOption();
    }

    @Override
    void showOptionsAfterAddingIngredient() {
        showOptionsDuringEditing();
    }

    @Override
    void handleOptionsAfterAddingIngredient() {
        handleRecipeEditOption();
    }

    @Override
    void showOptionsAfterAddingName() {
        askSaveOrContinue();
    }

}
