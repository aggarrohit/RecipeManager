package Utilities;

import Data.RecipePool;
import Data.Week;
import Data.WeekDay;
import RecipeKit.Recipe;

import java.util.*;

public  class Utils {
    public static int getCurrentWeek(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getCurrentWeekDay(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static Week getWeekWithUniqueRecipes(){
        Map<Integer,String> weekRecipes = new HashMap<>();
        String[] recipeIds = get7RecipeIds();
        for (int i = 1; i < 8; i++) {
            weekRecipes.put(i,recipeIds[i-1]);
        }


        Week week = new Week();
        week.setWeekRecipes(weekRecipes);

        return week;
    }

    private static String[] get7RecipeIds(){
        List<Recipe> recipeList = get7Recipes();
        String[] recipeIds = new String[7];
        int i=0;
        for(Recipe recipe:recipeList){
            recipeIds[i] = recipe.getId();
            i++;
        }
        return recipeIds;
    }

    public static List<Recipe> get7Recipes(){
        List<Recipe> recipeList = RecipePool.getRecipeList();
        List<Recipe> requiredRecipeList = new ArrayList<>();

        if(recipeList.size()>0){
            Collections.sort(recipeList);
        }

        return checkWeekAddRecipesToList(requiredRecipeList,1);
    }

    private static List<Recipe> checkWeekAddRecipesToList(List<Recipe> requiredRecipeList, int filterLevel) {
        List<Recipe> recipeList = RecipePool.getRecipeList();
//        return recipeList;
        int currentWeekNumber = getCurrentWeek();
        int[] matchingWeeks = new int[]{currentWeekNumber-1,currentWeekNumber,currentWeekNumber+1};
        List matchingWeeksList = Arrays.asList(matchingWeeks);

        for (Recipe recipe: recipeList){

            if(requiredRecipeList.size()==7) break;

            boolean recipeNotFoundInNearAndCurrentWeeks = filterLevel==1 && !matchingWeeksList.contains(recipe.getLastUsedInWeekNumber());
            boolean recipeNotFoundInCurrentWeek = filterLevel==2 && currentWeekNumber!=recipe.getLastUsedInWeekNumber();
            boolean notCheckingInNearbyWeeks = filterLevel == 3;

            if(recipeNotFoundInNearAndCurrentWeeks || recipeNotFoundInCurrentWeek || notCheckingInNearbyWeeks){
                requiredRecipeList.add(recipe);
                updateRecipeCounts(recipe);
            }

        }

        if(requiredRecipeList.size()==7){
            return requiredRecipeList;
        }else if(filterLevel==1){
            return checkWeekAddRecipesToList(requiredRecipeList,2);
        }else {
            return checkWeekAddRecipesToList(requiredRecipeList,3);
        }


    }

    private static void updateRecipeCounts(Recipe recipe){

        int recipeIndex = RecipePool.getRecipeList().indexOf(recipe);

        recipe.setOccurrenceCount(recipe.getOccurrenceCount()+1);
        recipe.setLastUsedInWeekNumber(getCurrentWeek());

        List<Recipe> poolRecipeList = RecipePool.getRecipeList();
        poolRecipeList.set(recipeIndex,recipe);
        RecipePool.setRecipeList(poolRecipeList);
    }

    public static String getWeekDayByNumber(int weekDayNumber){
        switch (weekDayNumber){
            case 1: return WeekDay.MONDAY.toString();
            case 2: return WeekDay.TUESDAY.toString();
            case 3: return WeekDay.WEDNESDAY.toString();
            case 4: return WeekDay.THURSDAY.toString();
            case 5: return WeekDay.FRIDAY.toString();
            case 6: return WeekDay.SATURDAY.toString();
            case 7: return WeekDay.SUNDAY.toString();
            default: return  "Not Found";
        }
    }

    public static boolean isWeekAcceptable(int weekNumber){
        if(weekNumber>=1 && weekNumber<=52){
            return true;
        }
        return false;
    }

    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
}
