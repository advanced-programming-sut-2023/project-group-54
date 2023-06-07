package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.SignupMenuController;
import com.ap.stronghold.view.enums.commands.Command;
import com.ap.stronghold.view.enums.commands.CommandHandler;
import com.ap.stronghold.view.enums.messages.SignupMenuMessage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class SignupMenu extends Application {

    public TextField nickname;
    public PasswordField password;
    public TextField passwordText;
    public boolean visiblePassword;
    public TextField username;
    public TextField email;
    public ChoiceBox recoveryQuestion;
    public Text message;
    public Text usernameCheck;
    public Text passwordCheck;
    public CheckBox haveSlogan;
    public TextField slogan;
    public ToggleGroup commonSlogan;
    public Button randomSlogan;
    public Text nicknameCheck;
    public Text emailCheck;
    public HBox commonSloganHBox;
    public int questionNumber = 0;
    public String questionAnswer = "";
    public static Pane pane;

    @Override
    public void start(Stage stage) throws IOException {
        pane = FXMLLoader.load(LoginMenu.class.getResource("/com/ap/stronghold/FXML/signUpMenu.fxml"));
        visiblePassword = false;
        Scene scene = new Scene(pane);

        stage.setScene(scene);
    }

    public void back() throws Exception {
        (new MainMenu()).start(MainMenu.stage);
    }

    public void signUp() {
        SignupMenuMessage result = SignupMenuController.setUser(username.getText()
                , password.getText(), nickname.getText(), email.getText(), slogan.getText());
        switch (result) {
            case SECURITY_QUESTION:
                setSecurityQuestion();
                break;
        }
        initialize();
    }

    private void setSecurityQuestion() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("security question");
        dialog.setHeaderText("select security question");

        ChoiceBox<String> order = new ChoiceBox<String>();
        order.getItems().add("What is your father’s name?");
        order.getItems().add("What was your first pet’s name?");
        order.getItems().add("What is your mother’s last name?");
        order.setValue("What is your father’s name?");
        HBox content = new HBox();
        content.setSpacing(10);
        content.getChildren().addAll(new Label("select your security question"), order);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return order.getValue();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            questionNumber = 0;
            switch (order.getValue()){
                case "What is your father’s name?" -> questionNumber = 1;
                case "What is your mother’s last name?" -> questionNumber = 2;
                case "What was your first pet’s name?" -> questionNumber = 3;
            }
            setSecurityQuestionAnswer();
        }else{
            message.setFill(Color.RED);
            message.setText("failed during sign up");
        }
    }

    private void setSecurityQuestionAnswer() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("security question answer");
        dialog.setHeaderText("enter answer to question below");
        switch (questionNumber) {
            case 1 -> dialog.setContentText("What is your father’s name?");
            case 2 -> dialog.setContentText("What was your first pet’s name?");
            case 3 -> dialog.setContentText("What is your mother’s last name?");
        }
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            questionAnswer = result.get();
            SignupMenuMessage createUserResult = SignupMenuController.createUser(questionNumber, questionAnswer);
            if(createUserResult != SignupMenuMessage.SUCCESS) {
                message.setFill(Color.RED);
                message.setText("failed because of wrong answers for captcha");
            }else{
                message.setFill(Color.GREEN);
                message.setText("sign up successfully");
            }
        }else{
            message.setFill(Color.RED);
            message.setText("failed during sign up");
        }
    }

    public void changeHaveSlogan() {
        if(!haveSlogan.isSelected()){
            slogan.setVisible(false);
            slogan.setText("");
            randomSlogan.setVisible(false);
            commonSlogan.selectToggle(null);
            commonSloganHBox.setVisible(false);
        }else{
            commonSloganHBox.setVisible(true);
            slogan.setVisible(true);
            randomSlogan.setVisible(true);
        }
    }

    public void changeRandomSlogan() {
        slogan.setText(SignupMenuController.getRandomSlogan());
        commonSlogan.selectToggle(null);
    }

    public void commonSloganSelect() {
        slogan.setText(commonSlogan.getSelectedToggle().getUserData().toString());
    }

    public void initialize(){
        emailCheck.setFill(Color.RED);
        nicknameCheck.setFill(Color.RED);
        passwordCheck.setFill(Color.RED);
        usernameCheck.setFill(Color.RED);

        if(username.getText().isEmpty())
            usernameCheck.setText("username not entered");
        if(password.getText().isEmpty())
            passwordCheck.setText("password not entered");
        if(nickname.getText().isEmpty())
            nicknameCheck.setText("nickname not entered");
        if(email.getText().isEmpty())
            emailCheck.setText("email not entered");

        username.textProperty().addListener((observable, oldText, newText)->{
            if(username.getText().isEmpty())
                usernameCheck.setText("username not entered");
            else {
                SignupMenuMessage result = Controller.checkUsernameValidity(username.getText());
                usernameCheck.setText(getError(result));
            }
        });
        password.textProperty().addListener((observable, oldText, newText)->{
            if(password.getText().isEmpty())
                passwordCheck.setText("password not entered");
            else{
                SignupMenuMessage result = Controller.checkPasswordValidity(password.getText());
                passwordCheck.setText(getError(result));
            }
        });
        nickname.textProperty().addListener((observable, oldText, newText)->{
            if(!nickname.getText().isEmpty())
                nicknameCheck.setText("");
        });
        email.textProperty().addListener((observable, oldText, newText)->{
            if(email.getText().isEmpty())
                emailCheck.setText("email not entered");
            else {
                SignupMenuMessage result = Controller.checkEmailValidity(email.getText());
                emailCheck.setText(getError(result));
            }
        });
        slogan.textProperty().addListener((observable, oldText, newText)->{
            commonSlogan.selectToggle(null);
        });
    }

    public String getError(SignupMenuMessage result){
        String message = "";

        switch (result){
            case EMAIL_EXIST:
                message = "this email already exists";
                break;
            case WRONG_FORMAT_USERNAME:
                message = "invalid format for username";
                break;
            case USERNAME_EXIST:
                message = "this username already exists";
                break;
            case WRONG_FORMAT_PASSWORD_LENGTH:
                message = "password length is too low at least 6 is needed";
                break;
            case WRONG_FORMAT_PASSWORD_LETTERS:
                message = "your password should contain uppercase and lowercase letters and numbers";
                break;
            case WRONG_FORMAT_PASSWORD_SPECIAL:
                message = "your password should contain special letters";
                break;
            case WRONG_FORMAT_EMAIL:
                message = "invalid format for email";
                break;
        }
        return message;
    }

    public void getRandomPassword() {
        String randomPassword = SignupMenuController.createRandomPassword();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("password");
        alert.setContentText("your random password is: " + randomPassword);
        Optional<ButtonType> option = alert.showAndWait();
        if(ButtonType.OK.equals(option.get())){
            password.setText(randomPassword);
        }
    }

    public void togglePasswordVisible() {
        if(!visiblePassword){
            visiblePassword = true;
            int index = 0;
            int index2 = 0;
            for (Node child : pane.getChildren()) {
                if(child instanceof HBox){
                    index = ((HBox) child).getChildren().indexOf(password);
                    if(index != -1){
                        index2 = pane.getChildren().indexOf(child);
                        break;
                    }
                }
            }
            String passwordVal = password.getText();
            ((HBox) pane.getChildren().get(index2)).getChildren().remove(password);
            passwordText = new TextField();
            passwordText.setText(passwordVal);
            passwordText.setPromptText("password");
            ((HBox) pane.getChildren().get(index2)).getChildren().add(index, passwordText);
        }else{
            visiblePassword = false;
            int index = 0;
            int index2 = 0;
            for (Node child : pane.getChildren()) {
                if(child instanceof HBox){
                    index = ((HBox) child).getChildren().indexOf(passwordText);
                    if(index != -1){
                        index2 = pane.getChildren().indexOf(child);
                        break;
                    }
                }
            }
            String passwordVal = passwordText.getText();
            ((HBox) pane.getChildren().get(index2)).getChildren().remove(passwordText);
            password = new PasswordField();
            password.setText(passwordVal);
            password.setPromptText("password");
            ((HBox) pane.getChildren().get(index2)).getChildren().add(index, password);
        }
    }
}
