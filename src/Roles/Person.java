package Roles;

import Data.RecipePool;
import Menus.MenuController;
import Menus.MenusView;

public abstract class Person {

    public void viewRecipe(int recipeIndex){
        System.out.println(RecipePool.getRecipeByIndex(recipeIndex));
    };
    public void viewRecipePool(){
        System.out.println(RecipePool.printRecipeNames());
    };

}
