import controller.Controller;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller.setScanner(scanner);
        Controller.run();
    }
}
