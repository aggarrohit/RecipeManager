import Data.RecipePool;
import Data.Weeks;
import Menus.MenuController;
import Menus.MenusModel;
import Menus.MenusView;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MenusView menusView = new MenusView();
        MenusModel menusModel = new MenusModel();
        Scanner scanner = new Scanner(System.in);

        //sample recipes are added when RecipePool is initialized
        new RecipePool();
        new Weeks();

        MenuController controller = new MenuController(scanner, menusView, menusModel);
        menusView.drawMainMenu();
        proceedWithMainMenuLoop(menusView, controller);
    }

    private static void proceedWithMainMenuLoop(MenusView menusView, MenuController controller) {
        try {
            controller.handelMainMenu();
        } catch (NumberFormatException e) {
            proceedWithMainMenuLoop(menusView, controller);
        } catch (IndexOutOfBoundsException e) {
            menusView.showInvalidInput();
            proceedWithMainMenuLoop(menusView, controller);
        }
    }



}