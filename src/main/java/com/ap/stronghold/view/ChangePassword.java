package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.LoginMenuController;
import com.ap.stronghold.controller.ProfileMenuController;
import com.ap.stronghold.controller.SignupMenuController;
import com.ap.stronghold.view.enums.messages.LoginMenuMessage;
import com.ap.stronghold.view.enums.messages.ProfileMenuMessage;
import com.ap.stronghold.view.enums.messages.SignupMenuMessage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class ChangePassword extends Application {
    public PasswordField oldPassword;
    public Text passwordCheck;
    public PasswordField newPassword;
    public PasswordField newPasswordConfirmation;
    private static Stage stageIn;
    public TextField passwordText = new TextField();
    public TextField newPasswordText = new TextField();
    public TextField confirmationPasswordText = new TextField();
    public boolean visiblePassword;
    public static Pane pane;
    private boolean visiblePassword2;

    @Override
    public void start(Stage stage) throws Exception {
        stageIn = stage;
        pane = FXMLLoader.load(LoginMenu.class.getResource("/com/ap/stronghold/FXML/changePassword.fxml"));
        visiblePassword = false;
        visiblePassword2 =false;
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {

        newPassword.textProperty().addListener((observable, oldText, newText)->{
            if(newPassword.getText().isEmpty())
                passwordCheck.setText("password not entered");
            else{
                SignupMenuMessage result = Controller.checkPasswordValidity(newPassword.getText());
                passwordCheck.setText(SignupMenu.getError(result));
            }
        });
        newPasswordText.textProperty().addListener((observable, oldText, newText)->{
            if(newPasswordText.getText().isEmpty())
                passwordCheck.setText("password not entered");
            else{
                SignupMenuMessage result = Controller.checkPasswordValidity(newPasswordText.getText());
                passwordCheck.setText(SignupMenu.getError(result));
            }
        });

    }
    public void changePasswordMenu(MouseEvent mouseEvent) {
        String oldPass = visiblePassword ? passwordText.getText() : oldPassword.getText();
        String newPass = visiblePassword2 ? newPasswordText.getText() : newPassword.getText();
        String conPass = visiblePassword2 ? confirmationPasswordText.getText() : newPasswordConfirmation.getText();
        if (!passwordCheck.getText().equals("") || oldPass.equals("") ||
                newPass.equals("") || conPass.equals(""))
            ProfileMenu.showAlertError("change password error","you have error in new or old password");
        else if (LoginMenuController.passwordChecker(oldPass).equals(LoginMenuMessage.SUCCESS)){
            ProfileMenu.showAlertError("change password error","old password is not correct");
            if (!visiblePassword) oldPassword.setText("");
            else passwordText.setText("");
        }
        else if (newPass.equals(oldPass))
            ProfileMenu.showAlertError("change password error","new password is equal to old password");
        else if (!newPass.equals(conPass)) {
            ProfileMenu.showAlertError("change password error", "password and password confirmation not equal");
            if (!visiblePassword2) newPasswordConfirmation.setText("");
            else confirmationPasswordText.setText("");
        }
        else if (ProfileMenuController.changePassword(oldPass,newPass).equals(ProfileMenuMessage.SUCCESS)) {
            ProfileMenuController.changePasswordConfirmation(newPass,conPass);
            ProfileMenu.showAlertConfirm("change password", "password changed successfully");
            stageIn.close();
        }

    }

    public void backToProfile(MouseEvent mouseEvent) throws Exception {
        stageIn.close();
    }

    public void togglePasswordVisible() {
        if(!visiblePassword){
            visiblePassword = true;
            int index = 0;
            int index2 = 0;
            for (Node child : pane.getChildren()) {
                if(child instanceof HBox){
                    index = ((HBox) child).getChildren().indexOf(oldPassword);
                    if(index != -1){
                        index2 = pane.getChildren().indexOf(child);
                        break;
                    }
                }
            }
            String passwordVal = oldPassword.getText();
            ((HBox) pane.getChildren().get(index2)).getChildren().remove(oldPassword);
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
            oldPassword = new PasswordField();
            oldPassword.setText(passwordVal);
            oldPassword.setPromptText("password");
            ((HBox) pane.getChildren().get(index2)).getChildren().add(index, oldPassword);
        }
    }

    public void togglePasswordVisible2() {
        if(!visiblePassword2){
            visiblePassword2 = true;
            int index = 0;
            int index2 = 0;
            int index3 = 0;
            int index4 = 0;
            for (Node child : pane.getChildren()) {
                if(child instanceof HBox){
                    index = ((HBox) child).getChildren().indexOf(newPassword);
                    if(index != -1){
                        index2 = pane.getChildren().indexOf(child);
                        break;
                    }
                }
            }
            for (Node child : pane.getChildren()) {
                if(child instanceof HBox){
                    index3 = ((HBox) child).getChildren().indexOf(newPasswordConfirmation);
                    if(index3 != -1){
                        index4 = pane.getChildren().indexOf(child);
                        break;
                    }
                }
            }
            String passwordVal = newPassword.getText();
            String passwordVal2 = newPasswordConfirmation.getText();
            ((HBox) pane.getChildren().get(index2)).getChildren().remove(newPassword);
            ((HBox) pane.getChildren().get(index4)).getChildren().remove(newPasswordConfirmation);
            newPasswordText = new TextField();
            newPasswordText.setText(passwordVal);
            newPasswordText.setPromptText("password");
            ((HBox) pane.getChildren().get(index2)).getChildren().add(index, newPasswordText);
            confirmationPasswordText = new TextField();
            confirmationPasswordText.setText(passwordVal2);
            confirmationPasswordText.setPromptText("password");
            ((HBox) pane.getChildren().get(index4)).getChildren().add(index3, confirmationPasswordText);
        }else{
            visiblePassword2 = false;
            int index = 0;
            int index2 = 0;
            int index3 = 0;
            int index4 = 0;
            for (Node child : pane.getChildren()) {
                if(child instanceof HBox){
                    index = ((HBox) child).getChildren().indexOf(newPasswordText);
                    if(index != -1){
                        index2 = pane.getChildren().indexOf(child);
                        break;
                    }
                }
            }
            for (Node child : pane.getChildren()) {
                if(child instanceof HBox){
                    index3 = ((HBox) child).getChildren().indexOf(confirmationPasswordText);
                    if(index3 != -1){
                        index4 = pane.getChildren().indexOf(child);
                        break;
                    }
                }
            }
            String passwordVal = newPasswordText.getText();
            String passwordVal2 = confirmationPasswordText.getText();
            ((HBox) pane.getChildren().get(index2)).getChildren().remove(newPasswordText);
            ((HBox) pane.getChildren().get(index4)).getChildren().remove(confirmationPasswordText);
            newPassword = new PasswordField();
            newPassword.setText(passwordVal);
            newPassword.setPromptText("password");
            ((HBox) pane.getChildren().get(index2)).getChildren().add(index, newPassword);
            newPasswordConfirmation = new PasswordField();
            newPasswordConfirmation.setText(passwordVal2);
            newPasswordConfirmation.setPromptText("password");
            ((HBox) pane.getChildren().get(index4)).getChildren().add(index3, newPasswordConfirmation);
            initialize();
        }
    }
    public void getRandomPassword() {
        String randomPassword = SignupMenuController.createRandomPassword();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("password");
        alert.setContentText("your random password is: " + randomPassword);
        Optional<ButtonType> option = alert.showAndWait();
        if(ButtonType.OK.equals(option.get())){
            newPassword.setText(randomPassword);
        }
    }
}
