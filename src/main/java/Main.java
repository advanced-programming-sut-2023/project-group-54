import model.User;
import view.MainMenu;

public class Main {
    public static void main(String[] args) {
        User.loadUser();
        MainMenu menu = new MainMenu();
        menu.run();
    }
}
