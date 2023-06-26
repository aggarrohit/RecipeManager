package ui;

public  class WeekView {
    public static void drawWeeksList(int[] weeksList){
        System.out.println("Weeks List:-");
        int i=0;
        for (int weekNumber:weeksList){
            System.out.println("["+(weekNumber)+"] Week "+weekNumber);
            i++;
        }
    }
}
