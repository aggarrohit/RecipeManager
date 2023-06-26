package Roles;

import Menus.DietitianMenuController;
import RecipeKit.Recipe;

interface IDietitian {
    void createRecipe(DietitianMenuController dietitianMenuController);
    void editRecipe(int recipeIndex,DietitianMenuController dietitianMenuController);
}
