package Menus;

import Commons.CommonView;

public class MenusView extends CommonView {

    public void drawMainMenu(){
        clearTerminal();
        System.out.println("Foormora : Recipe Manager");
        System.out.println("Please select your role below");
        System.out.println("[1] User");
        System.out.println("[2] Dietitian");
        System.out.println("[3] Exit");
        System.out.print("Enter Option:");
    }



    public void requestRecipeNumber(){
        System.out.print("Select recipe number:");
    }


    public void askForOption(){
        System.out.print("\nEnter option: ");
    }


}
