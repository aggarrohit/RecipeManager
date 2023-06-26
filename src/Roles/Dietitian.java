package Roles;

import Menus.DietitianMenuController;
import Menus.MenuController;
import Menus.MenusModel;
import Menus.MenusView;
import RecipeKit.CreateRecipe;
import RecipeKit.EditRecipe;

public class Dietitian extends Person implements IDietitian {

    MenusView menusView;
    MenusModel menusModel;
    MenuController controller;

    public Dietitian(MenusView menusView, MenusModel menusModel, MenuController controller) {
        this.menusView = menusView;
        this.menusModel = menusModel;
        this.controller = controller;
    }

    @Override
    public void createRecipe(DietitianMenuController dietitianMenuController) {
        new CreateRecipe(dietitianMenuController);
    }

    @Override
    public void editRecipe(int recipeIndex, DietitianMenuController dietitianMenuController){
        new EditRecipe(dietitianMenuController,recipeIndex);
    }


}
