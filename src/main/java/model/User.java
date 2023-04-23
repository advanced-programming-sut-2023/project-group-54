package model;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private int questionNumber;
    private Government government;
    private String questionAnswer;

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password, String nickname, String email, String slogan,
                int questionNumber, String questionAnswer) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        this.questionNumber = questionNumber;
        this.questionAnswer = questionAnswer;
    }

    public String getUsername() {
        return username;
    }

    public boolean isPasswordValid(String password) {
        return password.equals(this.getPassword())
    }
    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public Government getGovernment() {
        return government;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setMap(int mapNumber) {

    }
}
