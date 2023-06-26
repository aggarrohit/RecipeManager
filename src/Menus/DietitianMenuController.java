package Menus;

import Roles.Dietitian;
import java.util.Scanner;

public class DietitianMenuController extends MenuController{

    Dietitian dietitian;

    public DietitianMenuController(Scanner scanner, MenusView menusView, MenusModel menusModel) {
        super(scanner, menusView, menusModel);
        dietitian = new Dietitian(menusView, menusModel,this);

        menusView.clearTerminal();
        showDietitianMenu();
    }

    public void showDietitianMenu(){
        menusView.drawMenuOptions(menusModel.dietitianOptions);
        requestNumberInput();
        proceedWithSelectedTask();
    }

    private void proceedWithSelectedTask() {
        int selectedOption = menusModel.getSelectedOption();
        switch (selectedOption) {
            case 1 -> dietitian.createRecipe(this);
            case 2 -> {
                dietitian.viewRecipePool();
                showThisOptionWithDietitianMenu("View Recipe");
                handleRecipePoolOption();
            }
            case 3 -> goToMainMenu();
            default -> {
                menusView.showInvalidInput();
                showDietitianMenu();
            }
        }
    }

    private void showThisOptionWithDietitianMenu(String option){
        menusView.drawMenuOptions(new String[]{option,"Go to dietitian menu"});
    }

    private void handleRecipePoolOption() {
        try {
            requestNumberInput();
            int selectedOption = menusModel.getSelectedOption();
            switch (selectedOption) {
                case 1 -> dietitianRequestRecipeNumber();
                case 2 -> {
                    menusView.clearTerminal();
                    showDietitianMenu();
                }
                default -> throw new IndexOutOfBoundsException();
            }
        }catch (IndexOutOfBoundsException e){
            menusView.showInvalidInput();
            handleRecipePoolOption();
        }
    }

    private void dietitianRequestRecipeNumber() {
        menusView.requestRecipeNumber();
        requestNumberInput();
        int recipeIndex = menusModel.getSelectedOption()-1;
        dietitian.viewRecipe(recipeIndex);
        showOptionsAfterRecipeView();
        handleRecipeDetailsOption();
    }


    private void showOptionsAfterRecipeView(){
        menusView.drawMenuOptions(new String[]{ "Edit recipe",
                                                "View another recipe",
                                                "Go to dietitian menu"
                                                });
    }

    private void handleRecipeDetailsOption() {
        requestNumberInput();
        try {
            int selectedOption = menusModel.getSelectedOption();
            switch (selectedOption) {
                case 1 -> {
                    int recipeIndex = menusModel.getSelectedOption() - 1;
                    dietitian.editRecipe(recipeIndex, this);
                }
                case 2 -> dietitianRequestRecipeNumber();
                case 3 -> {
                    menusView.clearTerminal();
                    showDietitianMenu();
                }
                default -> throw new IndexOutOfBoundsException();
            }
        }catch (IndexOutOfBoundsException e){
            menusView.showInvalidInput();
            handleRecipeDetailsOption();
        }
    }



}
