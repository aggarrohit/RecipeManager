package RecipeKit;

import Commons.CommonView;
import Data.RecipePool;
import Data.Week;

import java.util.Map;

public class RecipeView extends CommonView {

    public void askRecipeName(){
        System.out.print("Enter recipe name: ");
    }

    public void askStepNumberToDelete(){
        System.out.print("Enter step number to delete: ");
    }

    public void askIngredientNumberToDelete(){
        System.out.print("Enter ingredient number to delete: ");
    }

    public static void drawWeekRecipes(Week week){
        Map<Integer,String> weekRecipes = week.getWeekRecipes();

        for (Map.Entry<Integer,String> dayRecipe:weekRecipes.entrySet()){
            String weekDay = "["+dayRecipe.getKey()+"] "
                    + Utilities.Utils.getWeekDayByNumber(dayRecipe.getKey());

            Recipe recipe = RecipePool.getRecipeById(dayRecipe.getValue());
            String recipeName = recipe.getName();
            System.out.println(weekDay+": "+recipeName);
        }

    }

}
