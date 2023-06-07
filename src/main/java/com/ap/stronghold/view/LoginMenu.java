package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.LoginMenuController;
import com.ap.stronghold.view.enums.commands.Command;
import com.ap.stronghold.view.enums.commands.CommandHandler;
import com.ap.stronghold.view.enums.messages.LoginMenuMessage;
import com.ap.stronghold.view.enums.messages.SignupMenuMessage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class LoginMenu extends Application{
    public TextField username;
    public PasswordField password;
    public Text error;
    public CheckBox stayLoggedIn;


    private String passwordErrorsGet(SignupMenuMessage signupMenuMessage) {
        switch (signupMenuMessage) {
            case WRONG_FORMAT_PASSWORD_LENGTH -> {
                return "password length is too low at least 6 is needed";
            }
            case WRONG_FORMAT_PASSWORD_LETTERS -> {
                return "your password should contain uppercase and lowercase letters and numbers";
            }
            case WRONG_FORMAT_PASSWORD_SPECIAL -> {
                return "your password should have at least a special character";
            }
        }
        return "wrong password format";
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(LoginMenu.class.getResource("/com/ap/stronghold/FXML/loginMenu.fxml"));
        Scene scene = new Scene(pane);

        stage.setScene(scene);
    }

    public void signIn() throws Exception {
        LoginMenuMessage result = LoginMenuController.loginUser(username.getText(), password.getText(), stayLoggedIn.isSelected());
        switch (result) {
            case SUCCESS:
                (new Menu()).start(MainMenu.stage);
                break;
            case WRONG_PASSWORD:
                error.setText("wrong password");
                break;
            case USER_NOT_FOUND:
                error.setText("no user with this id exists");
                break;
            case FAILED_DURING_CAPTCHA:
                error.setText("failed because wrong answers for captcha");
                break;
        }
    }

    public void back() throws Exception {
        (new MainMenu()).start(MainMenu.stage);
    }

    public void forgotPassword() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Forgot Password");
        dialog.setHeaderText("Enter Username");
        dialog.setContentText("Enter Your Username Inside Field");

        Optional<String> result = dialog.showAndWait();

        result:
        while (result.isPresent()){
            LoginMenuMessage checkUsername = LoginMenuController.forgetPasswordUsernameCheck(result.get());
            int questionNumber = 0;
            switch (checkUsername) {
                case SUCCESS -> {
                    questionNumber = LoginMenuController.getQuestion(result.get());
                    askQuestion(questionNumber);
                    return;
                }
                case USER_NOT_FOUND -> {
                    dialog.setHeaderText("username not found");
                    result = dialog.showAndWait();
                }
            }
        }
    }

    private void askQuestion(int questionNumber) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Forgot Password");
        dialog.setHeaderText("Enter Answer To Question Below");
        switch (questionNumber) {
            case 1 -> dialog.setContentText("What is your father’s name?");
            case 2 -> dialog.setContentText("What was your first pet’s name?");
            case 3 -> dialog.setContentText("What is your mother’s last name?");
        }
        Optional<String> result = dialog.showAndWait();
        while (result.isPresent()){
            if (LoginMenuController.checkAnswer(result.get())) {
                askNewPassword();
                return;
            }else{
                dialog.setHeaderText("wrong answer");
                result = dialog.showAndWait();
            }
        }
    }

    private void askNewPassword() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Forgot Password");
        dialog.setHeaderText("Enter New Password");
//        dialog.setContentText("Enter New Password Inside Field");

        PasswordField pwd = new PasswordField();
        HBox content = new HBox();
        content.setSpacing(10);
        content.getChildren().addAll(new Label("Enter New Password Inside Field"), pwd);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return pwd.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        while (result.isPresent()){
            if (Controller.checkPasswordValidity(result.get()) == SignupMenuMessage.SUCCESS) {
                LoginMenuController.setNewPassword(result.get());
                return;
            }else{
                dialog.setHeaderText(passwordErrorsGet(Controller.checkPasswordValidity(result.get())));
                result = dialog.showAndWait();
            }
        }
    }

    public void initialize(){
        error.setFill(Color.RED);
    }

}
