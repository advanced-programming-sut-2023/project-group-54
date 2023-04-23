package view;
import controller.*;
import view.commands.outputs.ProfileMenuOutput;

import java.util.regex.Matcher;

public class ProfileMenu {
    private ProfileMenuController profileMenuController;

    public ProfileMenu() {
        this.profileMenuController = new ProfileMenuController();
    }

    public String run() {
        String command;
        Matcher matcher;
        while (true) {
//            command = Menu.getScanner().nextLine();
            //if current menu
            //if back
            //if change username
            //if change nickname
            //if change password
            //if change email
            //if change slogan and remove slogan
            //if high score
            //if rank
            //if  slogan
            //if all details
        }
    }

    public void changeUsername(Matcher matcher) {

    }

    public void changeNickname(Matcher matcher) {

    }

    public void changePassword(Matcher matcher) {

    }

    public void changePasswordSecondTimeChecker(Matcher matcher) {

    }

    public void changeEmail(Matcher matcher) {

    }

    public void changeSlogan(Matcher matcher) {

    }

}
