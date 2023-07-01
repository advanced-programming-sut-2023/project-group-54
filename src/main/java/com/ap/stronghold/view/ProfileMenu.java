package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.LoginMenuController;
import com.ap.stronghold.controller.ProfileMenuController;
import com.ap.stronghold.controller.SignupMenuController;
import com.ap.stronghold.model.User;
import com.ap.stronghold.view.enums.messages.ProfileMenuMessage;
import com.ap.stronghold.view.enums.messages.SignupMenuMessage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenu extends Application implements DropTargetListener{
    public TextField nickname;

    public TextField username;
    public TextField email;
    public ChoiceBox recoveryQuestion;

    public Text usernameCheck;
    public CheckBox haveSlogan;
    public TextField slogan;
    public ToggleGroup commonSlogan;
    public Button randomSlogan;
    public Text nicknameCheck;
    public Text emailCheck;
    public TextField oldPassword;
    public TextField newPassword;
    public Text passwordCheck;
    public Text highScore;
    public HBox commonSloganHBox;
    public RadioButton avatar6 = new RadioButton();
    @FXML
    private RadioButton avatar0;
    @FXML
    private RadioButton avatar1;
    @FXML
    private RadioButton avatar2;
    @FXML
    private RadioButton avatar3;
    @FXML
    private RadioButton avatar4;
    @FXML
    private RadioButton avatar5;
    @FXML
    private ToggleGroup avatar;

    private Image[] avatars = new Image[6];
    private ImageView[] view = new ImageView[6];
    private Circle[] circles = new Circle[6];
    private ImageView selfPhoto = new ImageView();
    private Circle selfCircle = new Circle();
    private Pane pane = new Pane();
    public int questionNumber = 0;
    public String questionAnswer = "";
    private DragDropFrame dragFile = new DragDropFrame(false);
//    public static void run() {
//
//        System.out.println("you are in profile menu");
//        while (true) {
//            String command;
//            HashMap<String, ArrayList<String>> options;
//
//            command = MainMenu.getScanner().nextLine();
//            if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
//                System.out.println("profile menu");
//            else if (CommandHandler.parsCommand(Command.BACK, command) != null)
//                return;
//            else if ((options = CommandHandler.parsCommand(Command.CHANGE_USERNAME, command)) != null)
//                changeUsername(options);
//            else if ((options = CommandHandler.parsCommand(Command.CHANGE_NICKNAME, command)) != null)
//                changeNickname(options);
//            else if ((options = CommandHandler.parsCommand(Command.CHANGE_PASSWORD, command)) != null)
//                changePassword(options);
//            else if ((options = CommandHandler.parsCommand(Command.CHANGE_EMAIL, command)) != null)
//                changeEmail(options);
//            else if ((options = CommandHandler.parsCommand(Command.CHANGE_SLOGAN, command)) != null)
//                changeSlogan(options);
//            else if (CommandHandler.parsCommand(Command.REMOVE_SLOGAN, command) != null)
//                removeSlogan();
//            else if (CommandHandler.parsCommand(Command.PROFILE_DISPLAY_HIGH_SCORE, command) != null)
//                showHighScore();
//            else if (CommandHandler.parsCommand(Command.PROFILE_DISPLAY_RANK, command) != null)
//                showRank();
//            else if (CommandHandler.parsCommand(Command.DISPLAY_SLOGAN, command) != null)
//                displaySlogan();
//            else if (CommandHandler.parsCommand(Command.DISPLAY_PROFILE, command) != null)
//                displayProfile();
//            else
//                System.out.println("Invalid command in profile menu");
//        }
//    }


    //    public static void main(String[] args) {
//        launch();
//    }
    private static void changeUsername(HashMap<String, ArrayList<String>> options) {
        String username = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "u":
                    username = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if (username == null) {
            System.out.println("username not entered");
            return;
        }
        SignupMenuMessage result = ProfileMenuController.changeUsername(username);
        switch (result) {
            case WRONG_FORMAT_USERNAME -> System.out.println("invalid format for username");
            case USERNAME_EXIST -> System.out.println("username already exists");
            case SUCCESS -> System.out.println("username changed successfully");
        }
        User.saveUser();
    }

    private static void changeNickname(HashMap<String, ArrayList<String>> options) {
        String nickname = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "n":
                    nickname = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if (nickname == null) {
            System.out.println("nickname not entered");
            return;
        }
        ProfileMenuMessage result = ProfileMenuController.changeNickname(nickname);
        if (result.equals(ProfileMenuMessage.SUCCESS)) System.out.println("nickname changed successfully");
        User.saveUser();
    }

    public static void changePassword(HashMap<String, ArrayList<String>> options) {
        String oldPassword = null;
        String newPassword = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "o":
                    oldPassword = Controller.buildParameter(options.get(s).get(0));
                    break;
                case "n":
                    newPassword = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if (oldPassword == null) {
            System.out.println("oldPassword not entered");
            return;
        } else if (newPassword == null) {
            System.out.println("newPassword not entered");
            return;
        }
        ProfileMenuMessage result = ProfileMenuController.changePassword(oldPassword, newPassword);
        switch (result) {
            case PASSWORD_IS_NOT_CORRECT -> System.out.println("Current password is incorrect");
            case PASSWORD_IS_NOT_NEW -> System.out.println("Please enter a new password");
            case SUCCESS -> {
                if (!MainMenu.captchaChecker()) {
                    System.out.println("failed because of multiple wrong answers for captcha please try again from beginning");
                    return;
                } else {
                    System.out.println("please enter your new password again");
                    String newPasswordConfirmation = MainMenu.getScanner().nextLine();
                    ProfileMenuMessage confirmationResult = ProfileMenuController.changePasswordConfirmation(newPassword, newPasswordConfirmation);
                    if (confirmationResult.equals(ProfileMenuMessage.SUCCESS)) {
                        System.out.println("password changed successfully");
                    } else System.out.println("password confirmation failed please try again from beginning");

                }
            }
            default -> passwordErrorsPrint(result);
        }
        User.saveUser();
    }

    private static void passwordErrorsPrint(ProfileMenuMessage profileMenuMessage) {
        switch (profileMenuMessage) {
            case WRONG_FORMAT_PASSWORD_LENGTH:
                System.out.println("password length is too low at least 6 is needed");
                break;
            case WRONG_FORMAT_PASSWORD_LETTERS:
                System.out.println("your password should contain uppercase and lowercase letters and numbers");
                break;
            case WRONG_FORMAT_PASSWORD_SPECIAL:
                System.out.println("your password should have at least a special character");
                break;
        }
    }

    public static void changeEmail(HashMap<String, ArrayList<String>> options) {
        String email = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "e":
                    email = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if (email == null) {
            System.out.println("email not entered");
            return;
        }
        SignupMenuMessage result = ProfileMenuController.changeEmail(email);
        switch (result) {
            case WRONG_FORMAT_EMAIL -> System.out.println("invalid format for email");
            case EMAIL_EXIST -> System.out.println("email already exists");
            case SUCCESS -> System.out.println("email changed successfully");
        }
        User.saveUser();
    }

    public static void changeSlogan(HashMap<String, ArrayList<String>> options) {
        String slogan = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "s":
                    slogan = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if (slogan == null) {
            System.out.println("slogan not entered");
            return;
        }
        ProfileMenuMessage result = ProfileMenuController.changeSlogan(slogan);
        if (result.equals(ProfileMenuMessage.SUCCESS)) System.out.println("slogan changed successfully");
        User.saveUser();
    }

    public static void removeSlogan() {
        ProfileMenuMessage result = ProfileMenuController.removeSlogan();
        if (result.equals(ProfileMenuMessage.SUCCESS)) System.out.println("slogan removed successfully");
        User.saveUser();
    }

    public static void showHighScore() {
        int highScore = Controller.getLoggedInUser().getHighScore();
        System.out.println(highScore);
    }

    public static void showRank() {
        int rank = Controller.getLoggedInUser().getUserRank();
        System.out.println(rank);
    }

    public static void displaySlogan() {
        String result = ProfileMenuController.displaySlogan();
        System.out.println(result);

    }

    public static void displayProfile() {
        String result = ProfileMenuController.displayProfile();
        System.out.println(result.trim());
    }


    @Override
    public void drop(DropTargetDropEvent event) {
        event.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable transferable = event.getTransferable();
        DataFlavor[] flavors = transferable.getTransferDataFlavors();
        for (DataFlavor flavor : flavors) {
            try {
                if (flavor.isFlavorJavaFileListType()) {
                    java.util.List<Object> files = Arrays.asList(transferable.getTransferData(flavor));
                    //List files = (List) transferable.getTransferData(flavor);
                    String string = files.get(0).toString();
                    string = string.substring(1,string.length() -1);
                    Pattern pattern = Pattern.compile("(.jpg$)|(.png$)");
                    Matcher matcher = pattern.matcher(string);
                    if (matcher.find()) {
                        ProfileMenuController.setPhoto(string);
                        setSelfPhoto(pane);
                    } //else
                        //ProfileMenu.showAlertError("format error","you have to choose a jpg or png file");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        event.dropComplete(true);
    }

    @Override
    public void dragEnter(DropTargetDragEvent event) {
    }

    @Override
    public void dragExit(DropTargetEvent event) {
    }

    @Override
    public void dragOver(DropTargetDragEvent event) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent event) {
    }

    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(LoginMenu.class.getResource("/com/ap/stronghold/FXML/profileMenu.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }


    @FXML
    public void initialize() {

        emailCheck.setFill(Color.RED);
        nicknameCheck.setFill(Color.RED);
        usernameCheck.setFill(Color.RED);
        username.setText(Controller.getLoggedInUser().getUsername());
        nickname.setText(Controller.getLoggedInUser().getNickname());
        email.setText(Controller.getLoggedInUser().getEmail());
        slogan.setText(Controller.getLoggedInUser().getSlogan());
        username.textProperty().addListener((observable, oldText, newText) -> {
            if (username.getText().isEmpty())
                usernameCheck.setText("username not entered");
            else {
                SignupMenuMessage result = Controller.checkUsernameValidity(username.getText());
                if (!result.equals(SignupMenuMessage.USERNAME_EXIST) || !username.getText().equals(Controller.getLoggedInUser().getUsername())) {
                    usernameCheck.setText(SignupMenu.getError(result));
                } else {
                    usernameCheck.setText("");
                }
            }
        });

        nickname.textProperty().addListener((observable, oldText, newText) -> {
            if (nickname.getText().isEmpty())
                nicknameCheck.setText("nick name is empty");
            else nicknameCheck.setText("");
        });
        email.textProperty().addListener((observable, oldText, newText) -> {
            if (email.getText().isEmpty())
                emailCheck.setText("email not entered");
            else {
                SignupMenuMessage result = Controller.checkEmailValidity(email.getText());
                if (!result.equals(SignupMenuMessage.EMAIL_EXIST) || !email.getText().equals(Controller.getLoggedInUser().getEmail())) {
                    emailCheck.setText(SignupMenu.getError(result));
                } else {
                    emailCheck.setText("");
                }
            }
        });
        highScore.setText("high score is : " + Controller.getLoggedInUser().getHighScore());
        setAvatars(pane);


    }

    public RadioButton selectImage() {
        Image[] avatars = new Image[6];
        int toReturn = 5;
        //Controller.getLoggedInUser().getAvatarNumber();
        switch (toReturn) {
            case 0:
                return avatar0;
            case 1:
                return avatar1;
            case 2:
                return avatar2;
            case 3:
                return avatar3;
            case 4:
                return avatar4;
            case 5:
                return avatar5;
            default:
                return null;
        }
    }

    public void setAvatars(Pane pane) {
        for (int i = 0; i < 6; i++) {
            avatars[i] = new Image(ProfileMenu.class.getResource("/com/ap/stronghold/Media/avatars/"+i+".png").toExternalForm());
            view[i] = new ImageView(avatars[i]);
            circles[i] = new Circle(75 + i * 75 + 16, 360, 25);
            view[i].setClip(circles[i]);
            view[i].setX(50 + i * 75 + 16);
            view[i].setY(340);
            view[i].setFitWidth(50);
            view[i].setPreserveRatio(true);
            pane.getChildren().add(view[i]);
        }
        boolean sign = true;
        for (Image image : avatars) {
            if (image.getUrl().equals(Controller.getLoggedInUser().getAvatarPath())) {
                sign = false;
                break;
            }
        }
        if (sign) {
            setSelfPhoto(pane);
        }
        avatar0.setGraphic(view[0]);
        avatar1.setGraphic(view[1]);
        avatar2.setGraphic(view[2]);
        avatar3.setGraphic(view[3]);
        avatar4.setGraphic(view[4]);
        avatar5.setGraphic(view[5]);
        for (Toggle toggle : avatar.getToggles()) {
            if (((RadioButton) toggle).getGraphic() != null &&
                    ((ImageView) ((RadioButton) toggle).getGraphic()).getImage().getUrl().equals(Controller.getLoggedInUser().getAvatarPath()))
                ((RadioButton) toggle).setSelected(true);
        }

    }

    public void setSelfPhoto(Pane pane) {
        selfPhoto = new ImageView(Controller.getLoggedInUser().getAvatarPath());
        selfCircle = new Circle(91 + 6 * 75, 360, 25);
        selfPhoto.setClip(selfCircle);
        selfPhoto.setX(50 + 6 * 75 + 16);
        selfPhoto.setY(340);
        selfPhoto.setFitWidth(50);
        selfPhoto.setPreserveRatio(true);
        this.pane.getChildren().add(selfPhoto);
        avatar6.setGraphic(selfPhoto);
        avatar6.setSelected(true);
    }


    public void changePassword(MouseEvent mouseEvent) throws Exception {
        (new ChangePassword()).start(new Stage());
    }

    public void changeHaveSlogan() {
        if (!haveSlogan.isSelected()) {
            slogan.setVisible(false);
            slogan.setText("");
            randomSlogan.setVisible(false);
            commonSlogan.selectToggle(null);
            commonSloganHBox.setVisible(false);
        } else {
            commonSloganHBox.setVisible(true);
            slogan.setVisible(true);
            slogan.setText(Controller.getLoggedInUser().getSlogan());
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

    public static void showAlertError(String headerText, String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(alertText);
        alert.showAndWait();
    }

    public static void showAlertConfirm(String headerText, String alertText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(headerText);
        alert.setContentText(alertText);
        alert.showAndWait();
    }

    public void scoreBoard(MouseEvent mouseEvent) throws Exception {
        new ScoreBoard();
    }

    public void saveChanges(MouseEvent mouseEvent) throws Exception {
        if (usernameCheck.getText().equals("") && emailCheck.getText().equals("") && nicknameCheck.getText().equals("")) {
            ProfileMenuController.changeUsername(username.getText());
            ProfileMenuController.changeNickname(nickname.getText());
            ProfileMenuController.changeEmail(email.getText());
            if (haveSlogan.isSelected()) ProfileMenuController.changeSlogan(slogan.getText());
            else ProfileMenuController.changeSlogan("");
            for (Toggle toggle: avatar.getToggles()) {
                if (((RadioButton) toggle).getGraphic() != null &&
                        ((RadioButton)toggle).isSelected()) {
                    ProfileMenuController.setPhoto(((ImageView) ((RadioButton) toggle).getGraphic()).getImage().getUrl());
                    break;
                }
            }
            showAlertConfirm("changes saved","changes to avatar,username,email and nickname saved");
            (new Menu()).start(MainMenu.stage);
        }
    }

    public void askQuestion(MouseEvent mouseEvent) {
        int questionNumber = LoginMenuController.getQuestion(Controller.getLoggedInUser().getUsername());
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Change security question");
        dialog.setHeaderText("Enter Answer To Question Below");
        switch (questionNumber) {
            case 1 -> dialog.setContentText("What is your father’s name?");
            case 2 -> dialog.setContentText("What was your first pet’s name?");
            case 3 -> dialog.setContentText("What is your mother’s last name?");
        }
        Optional<String> result = dialog.showAndWait();
        while (result.isPresent()) {
            if (ProfileMenuController.checkAnswer(result.get())) {
                setSecurityQuestion();
                return;
            } else {
                dialog.setHeaderText("wrong answer");
                result = dialog.showAndWait();
            }
        }
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

        if (result.isPresent()) {
            questionNumber = 0;
            switch (order.getValue()) {
                case "What is your father’s name?" -> questionNumber = 1;
                case "What is your mother’s last name?" -> questionNumber = 2;
                case "What was your first pet’s name?" -> questionNumber = 3;
            }
            setSecurityQuestionAnswer();
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
        if (result.isPresent()) {
            questionAnswer = result.get();
            ProfileMenuController.setSecurityQuestion(questionNumber, questionAnswer);
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        (new Menu()).start(MainMenu.stage);

    }

    public void fileChooser(MouseEvent mouseEvent) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            ProfileMenuController.setPhoto(chooser.getSelectedFile().getPath());
            setSelfPhoto(pane);
        }
    }


    public void dragFile(MouseEvent mouseEvent) {
        dragFile = new DragDropFrame(true);
    }
}
class DragDropFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    public DragDropFrame(boolean b) {
        super("Drag and drop test");
        if (b) {
            this.setSize(250, 150);
            JLabel myLabel = new JLabel("Drag something here!", SwingConstants.CENTER);
            ProfileMenu profileMenu = new ProfileMenu();
            new DropTarget(myLabel, profileMenu);
            this.getContentPane().add(BorderLayout.CENTER, myLabel);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }
}
