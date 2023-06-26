package Menus;

import Roles.User;
import java.util.Scanner;

public class UserMenuController extends MenuController {

    User user;

    public UserMenuController(Scanner scanner, MenusView menusView, MenusModel menusModel) {
        super(scanner, menusView, menusModel);

        user = new User(menusView, menusModel,this);
        menusView.clearTerminal();
        showUserMenu();

    }


    public void showUserMenu(){
        menusView.drawMenuOptions(menusModel.userOptions);
        requestNumberInput();
        proceedWithSelectedTask();
    }

    public void proceedWithSelectedTask() {
        int selectedOption = menusModel.getSelectedOption();
        switch (selectedOption) {
            case 1 -> user.viewWeeks();
            case 2 -> user.currentWeekRecipes();
            case 3 -> user.createWeek();
            case 4 -> user.todayRecipe();
            case 5 -> {
                user.viewRecipePool();
                showThisOptionWithUserMenu("View Recipe");
                handleRecipePoolOption();
            }
            case 6 -> goToMainMenu();
            default -> {
                menusView.showInvalidInput();
                showUserMenu();
            }
        }
    }

    private void showThisOptionWithUserMenu(String option){
        menusView.drawMenuOptions(new String[]{option,"Go to user menu"});
    }

    private void handleRecipePoolOption() {
        try {
            requestNumberInput();
            int selectedOption = menusModel.getSelectedOption();
            switch (selectedOption) {
                case 1 -> {
                    menusView.requestRecipeNumber();
                    requestNumberInput();
                    user.viewRecipe(menusModel.getSelectedOption() - 1);
                    showThisOptionWithUserMenu("View Recipe");
                    handleRecipePoolOption();
                }
                case 2 -> {
                    menusView.clearTerminal();
                    showUserMenu();
                }
                default -> throw new IndexOutOfBoundsException();
            }
        }catch (IndexOutOfBoundsException e){
            menusView.showInvalidInput();
            handleRecipePoolOption();
        }
    }


}
