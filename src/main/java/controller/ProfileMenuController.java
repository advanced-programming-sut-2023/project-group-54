package controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import view.enums.messages.ProfileMenuMessage;
import view.enums.messages.SignupMenuMessage;

import java.io.*;

public class ProfileMenuController {
    private static User user = Controller.getLoggedInUser();


    public static SignupMenuMessage changeUsername(String username) {
        SignupMenuMessage result = Controller.checkUsernameValidity(username);
        if (result.equals(SignupMenuMessage.SUCCESS)) {
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            String filePath = new File("").getAbsolutePath().concat("/src/main/java/model/data/stayLoggedInUser.json");
            FileReader fileReader;
            try {
                fileReader = new FileReader(filePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            User user1 = gson.fromJson(fileReader, new TypeToken<User>() {}.getType());
            String oldUsername = user.getUsername();
            user.setUsername(username);
            if(user1 != null){
                if(oldUsername.equals(user1.getUsername())){
                    FileWriter fileWriter;
                    try {
                        fileWriter = new FileWriter(filePath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    gson.toJson(user, fileWriter);
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        User.saveUser();
        return result;
    }

    public static ProfileMenuMessage changeNickname(String newNickname) {
        user.setNickname(newNickname);
        User.saveUser();
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changePassword(String oldPassword, String newPassword) {
        if (!(user.isPasswordValid(oldPassword))) {
            return ProfileMenuMessage.PASSWORD_IS_NOT_CORRECT;
        }
        if (oldPassword.equals(newPassword)) {
            return ProfileMenuMessage.PASSWORD_IS_NOT_NEW;
        }
        SignupMenuMessage checkPasswordFormat = Controller.checkPasswordValidity(newPassword);
        switch (checkPasswordFormat) {
            case WRONG_FORMAT_PASSWORD_SPECIAL -> {
                return ProfileMenuMessage.WRONG_FORMAT_PASSWORD_SPECIAL;
            }
            case WRONG_FORMAT_PASSWORD_LENGTH -> {
                return ProfileMenuMessage.WRONG_FORMAT_PASSWORD_LENGTH;
            }
            case WRONG_FORMAT_PASSWORD_LETTERS -> {
                return ProfileMenuMessage.WRONG_FORMAT_PASSWORD_LETTERS;
            }
            case SUCCESS -> {
                return ProfileMenuMessage.SUCCESS;
            }
        }
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changePasswordConfirmation(String newPassword, String newPasswordConfirmation) {
        if (newPassword.equals(newPasswordConfirmation)) {
            user.setPassword(Controller.hashString(newPassword));
            User.saveUser();
            return ProfileMenuMessage.SUCCESS;
        } else return ProfileMenuMessage.PASSWORD_CONFIRMATION_FAILED;

    }

    public static SignupMenuMessage changeEmail(String newEmail) {
        SignupMenuMessage result = Controller.checkEmailValidity(newEmail);
        if (result.equals(SignupMenuMessage.SUCCESS)) {
            user.setEmail(newEmail);
            User.saveUser();
        }
        return result;

    }

    public static ProfileMenuMessage changeSlogan(String newSlogan) {
        user.setSlogan(newSlogan);
        User.saveUser();
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage removeSlogan() {
        user.setSlogan(null);
        User.saveUser();
        return ProfileMenuMessage.SUCCESS;
    }

    public static String showHighScore() {
        return "";
    }


    public static String displaySlogan() {
        String slogan = user.getSlogan();
        if (slogan == null) return "Slogan is empty!";
        else return slogan;


    }

    public static String displayProfile() {
        String result = "your Profile:\n";
        result += "username : " + user.getUsername() + "\n";
        result += "highScore : "+ user.getHighScore()+"\n";
        result += "rank : "+ user.getUserRank()+"\n";
        result += "nickname : " + user.getNickname() + "\n";
        result += "email : " + user.getEmail() + "\n";
        if (user.getSlogan() != null) {
            result += "slogan : " + user.getSlogan() + "\n";
        }
        return result;
    }
}
