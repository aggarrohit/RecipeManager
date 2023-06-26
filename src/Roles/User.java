package Roles;

import Data.RecipePool;
import Data.Week;
import Data.Weeks;
import Menus.MenusModel;
import Menus.MenusView;
import Menus.UserMenuController;
import RecipeKit.Recipe;
import RecipeKit.RecipeView;
import Utilities.Utils;
import ui.WeekView;

import java.util.Arrays;

public class User extends Person implements IUser {

    MenusView menusView;
    MenusModel menusModel;
    UserMenuController userMenuController;



    public User(MenusView menusView, MenusModel menusModel, UserMenuController controller) {
        this.menusView = menusView;
        this.menusModel = menusModel;
        this.userMenuController = controller;
    }

    @Override
    public void viewWeeks() {

        try {
            if(Weeks.weeksList.isEmpty()) throw new NullPointerException();
            WeekView.drawWeeksList(Weeks.getWeekNumbers());
            menusView.addGoBackOption();
            menusView.askForOption();
            userMenuController.requestNumberInput();
            handleWeeksListInput();
        }catch (NullPointerException e){
            menusView.clearTerminal();
            menusView.printThis("No week added!\n");
            userMenuController.showUserMenu();
        }

    }

    private void handleWeeksListInput() {
        int optionSelected = menusModel.getSelectedOption();
        if(optionSelected==0){
            menusView.clearTerminal();
            userMenuController.showUserMenu();
        }else{
            viewSelectedWeekRecipe(optionSelected);
        }
    }

    private void viewSelectedWeekRecipe(int weekNumber){
        Week week =null;
        try {
            week = Weeks.getWeekRecipesByWeekNumber(weekNumber);
            if(week==null) throw new NullPointerException();
            RecipeView.drawWeekRecipes(week);
            menusView.addGoBackOption();
            menusView.askForOption();
            userMenuController.requestNumberInput();
            handleWeekRecipesListInput(week);


        }catch (NullPointerException e){
            menusView.clearTerminal();
            menusView.printThis("Selected week not created!\n");
            userMenuController.showUserMenu();
        }catch (IndexOutOfBoundsException e){
            menusView.showInvalidInput();
            menusView.askForOption();
            userMenuController.requestNumberInput();
            handleWeekRecipesListInput(week);
        }


    }

    private void handleWeekRecipesListInput(Week week) {
        int optionSelected = menusModel.getSelectedOption();
        if(optionSelected==0){
            viewWeeks();
        }else{
            boolean invalidInput = menusModel.getSelectedOption()<0 || menusModel.getSelectedOption()>7;
            if(invalidInput) throw new IndexOutOfBoundsException();
            String selectedRecipeId = week.getWeekRecipes().get(menusModel.getSelectedOption());
            Recipe selectedRecipe = RecipePool.getRecipeById(selectedRecipeId);

            menusView.clearTerminal();
            menusView.printThis(selectedRecipe.toString());
            userMenuController.showUserMenu();
        }
    }

    @Override
    public void currentWeekRecipes() {
        viewSelectedWeekRecipe(Utilities.Utils.getCurrentWeek());
    }

    @Override
    public void createWeek() {
        menusView.askWeekNumber();
        userMenuController.requestNumberInput();

        handleCreateWeekNumberInput();
    }

    private void handleCreateWeekNumberInput(){
        try {
            boolean weekAlreadyExist = Utils.contains(Weeks.getWeekNumbers(),menusModel.getSelectedOption());
            if(weekAlreadyExist) throw new IllegalArgumentException();
            if (!Utils.isWeekAcceptable(menusModel.getSelectedOption())) throw new IndexOutOfBoundsException();
            Weeks.addWeekToList(menusModel.getSelectedOption(),Utils.getWeekWithUniqueRecipes());

            menusView.clearTerminal();
            menusView.printThis("Week Created!!");
            userMenuController.showUserMenu();
        }catch (IndexOutOfBoundsException e){
            menusView.showInvalidInput();
            createWeek();
        }catch (IllegalArgumentException e){
            menusView.printThis("This week already exists!");
            createWeek();
        }
    }

    @Override
    public void todayRecipe() {
        try {
            int currentWeekNumber = Utilities.Utils.getCurrentWeek();
            int currentWeekDay = Utilities.Utils.getCurrentWeekDay();
            String recipeId = Weeks.getWeekRecipesByWeekNumber(currentWeekNumber).getWeekRecipes().get(currentWeekDay);
            Recipe recipe = RecipePool.getRecipeById(recipeId);

            menusView.clearTerminal();
            menusView.printThis(recipe.toString());
            userMenuController.showUserMenu();
        }catch (NullPointerException | IndexOutOfBoundsException e){
            menusView.clearTerminal();
            menusView.printThis("Today's recipe not added!\n");
            userMenuController.showUserMenu();
        }
    }


}
