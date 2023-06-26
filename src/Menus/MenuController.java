package Menus;

import java.util.Scanner;

public class MenuController {
    Scanner scanner;
    MenusView menusView;
    MenusModel menusModel;

    public MenuController(Scanner scanner, MenusView menusView, MenusModel menusModel) {
        this.scanner = scanner;
        this.menusView = menusView;
        this.menusModel = menusModel;
    }

    public void goToMainMenu(){
        menusView.drawMainMenu();
        handelMainMenu();
    }

    public void handelMainMenu() {
        requestNumberInput();
        proceedWithMainOption();
    }

    public void requestNumberInput(){
        String input = scanner.nextLine();
        try {
            int optionSelected = Integer.parseInt(input.trim());
            menusModel.setSelectedOption(optionSelected);
        }catch (NumberFormatException exception){
            menusModel.setSelectedOption(-1);
        }
    }

    private void proceedWithMainOption() throws IndexOutOfBoundsException {
        int optionSelected = menusModel.getSelectedOption();

        switch (optionSelected){
            case 1 -> new UserMenuController(scanner, menusView, menusModel);
            case 2 -> new DietitianMenuController(scanner, menusView, menusModel);
            case 3 -> menusView.sayGoodBye();
            default -> throw new IndexOutOfBoundsException();
        }
    }


}
