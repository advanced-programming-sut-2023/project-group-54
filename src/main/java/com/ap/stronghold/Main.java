package com.ap.stronghold;

import com.ap.stronghold.model.User;
import com.ap.stronghold.view.MainMenu;

public class Main {
    public static void main(String[] args) {
        User.loadUser();
        MainMenu menu = new MainMenu();
        menu.run();
    }
}
