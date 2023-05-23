package controller;
import controller.Controller;
import model.Government;
import model.User;
import org.junit.jupiter.api.*;
import java.util.regex.Matcher;
import view.enums.messages.LoginMenuMessage;
import controller.LoginMenuController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

class LoginMenuControllerTest {

    @Test
    public void passwordCheckerTest() {
        User user = new User("beckham", Controller.hashString("elisa1@ARR"), "becki", "beckhamp@gmail.com", "shoot is the best way",
                1, "alibaba", new Government());
        assertEquals(LoginMenuMessage.WRONG_PASSWORD, LoginMenuController.loginUser("beckham", "Elisa1@ARR", true));
    }


    @Test
    public void userFoundTest() {
        User user = new User("beckham", Controller.hashString("elisa1@ARR"), "becki", "beckhamp@gmail.com", "shoot is the best way",
                1, "alibaba", new Government());
                assertEquals(LoginMenuMessage.USER_NOT_FOUND,LoginMenuController.loginUser("beham","elisa1@ARR",true));
    }
    @Test
    public void forgotPassword(){
        User user = new User("beckham", Controller.hashString("elisa1@ARR"), "becki", "beckhamp@gmail.com", "shoot is the best way",
                1, "alibaba", new Government());
                assertEquals(LoginMenuMessage.SUCCESS,LoginMenuController.forgetPasswordUsernameCheck("beckham"));
    }
    @Test
    public void checkQuestion(){
        User user = new User("beckham", Controller.hashString("elisa1@ARR"), "becki", "beckhamp@gmail.com", "shoot is the best way",
                1, "alibaba", new Government());
                assertEquals(1,LoginMenuController.getQuestion("beckham"));
    }
    @Test
    public void checkQuestionAnswer(){
        User user = new User("beckham", Controller.hashString("elisa1@ARR"), "becki", "beckhamp@gmail.com", "shoot is the best way",
                1, "alibaba", new Government());
                LoginMenuController.forgetPasswordUsernameCheck("beckham");
        assertEquals(true,LoginMenuController.checkAnswer("alibaba"));
    }
    @Test
    public void CheckLogout(){
        assertEquals(LoginMenuMessage.SUCCESS,LoginMenuController.logout());
    }

}

