package Data;

import java.util.HashMap;
import java.util.Map;

public class Weeks {
    public static Map<Integer,Week> weeksList;

    public Weeks() {
        weeksList = new HashMap<>();
    }

    public static Week getWeekRecipesByWeekNumber(int weekNumber){
        return weeksList.get(weekNumber);
    }

    public static void addWeekToList(int weekNumber,Week week){
        weeksList.put(weekNumber,week);
    }

    public static int[] getWeekNumbers(){
        int[] weekNumbers = new int[weeksList.size()];
        int i=0;
        for (int weekNumber:weeksList.keySet()){
            weekNumbers[i] = weekNumber;
            i++;
        }
        return weekNumbers;
    }
}
