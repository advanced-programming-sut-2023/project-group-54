package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.LoginMenuController;
import com.ap.stronghold.controller.ProfileMenuController;
import com.ap.stronghold.view.enums.messages.LoginMenuMessage;
import com.ap.stronghold.view.enums.messages.SignupMenuMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChangePassword extends Application {
    public TextField oldPassword;
    public Text passwordCheck;
    public TextField newPassword;
    public TextField newPasswordConfirmation;
    private Stage stageIn;

    @Override
    public void start(Stage stage) throws Exception {
        stageIn = stage;
        Pane pane = FXMLLoader.load(LoginMenu.class.getResource("/com/ap/stronghold/FXML/changePassword.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        newPassword.textProperty().addListener((observable, oldText, newText)->{
            if(newPassword.getText().isEmpty())
                passwordCheck.setText("password not entered");
            else if (newPassword.equals(oldPassword)) passwordCheck.setText("new password is equal to old password");
            else if (newPassword.equals(newPasswordConfirmation)) passwordCheck.setText("password and password confirmation not equal");
            else{
                SignupMenuMessage result = Controller.checkPasswordValidity(newPassword.getText());
                passwordCheck.setText(SignupMenu.getError(result));
            }
        });
    }
    public void changePasswordMenu(MouseEvent mouseEvent) {
        if (passwordCheck == null) ProfileMenu.showAlertError("change password error","you have error in new password");
        else if (LoginMenuController.passwordChecker(oldPassword.getText()).equals(LoginMenuMessage.SUCCESS)) {
            ProfileMenuController.changePassword(newPassword.getText(),newPasswordConfirmation.getText());
            ProfileMenu.showAlertConfirm("change password", "password changed successfully");
            stageIn.close();
        } else ProfileMenu.showAlertError("change password error","old password is not correct");

    }

    public void backToProfile(MouseEvent mouseEvent) throws Exception {

    }
}
