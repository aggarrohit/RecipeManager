package Commons;

public class CommonView {

    public void drawMenuOptions(String[] menuOptions){
        System.out.println("Please select task below");
        int i=0;
        for (String menuOption:menuOptions){
            System.out.println("["+(i+1)+"] "+menuOption);
            i++;
        }
        System.out.print("Enter Option: ");
    }

    public void showInvalidInput(){
        System.out.print("Invalid input!\n" +
                "Please enter valid input: ");
    }

    public void printThis(String txt){
        System.out.println(txt);
    }

    public void clearTerminal(){
//        try {
//            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
        try {
            String terminal = System.getenv("SHELL");
            if (terminal == null) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                throw new Exception("Found the shell terminal.");
            }
        } catch (Exception e) {
            String clearScreenASCIICode = "\033[H\033[2J";
            System.out.print(clearScreenASCIICode);
            System.out.flush();
        }
    }

    public void askForOption(){
        System.out.print("\nEnter option: ");
    }

    public void askWeekNumber(){
        System.out.print("\nEnter week number: ");
    }

    public void addGoBackOption(){
        System.out.println("[0] Go back");
    }

    public void sayGoodBye(){
        System.out.println("Bye bye..\nHave a nice day!!\n");
    }

}
