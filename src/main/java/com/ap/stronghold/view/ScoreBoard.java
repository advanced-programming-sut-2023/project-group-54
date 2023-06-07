package com.ap.stronghold.view;
//package com.ap.stronghold.view;
//
//import javafx.application.Application;
//import javafx.stage.Stage;
//
//import javax.swing.*;
//
//public class ScoreBoard extends Application {
//    @Override
//    public void start(Stage stage) throws Exception {
//
//    }
//}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import com.ap.stronghold.model.User;

public class ScoreBoard extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;

    public ScoreBoard() {
        setTitle("score board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"username", "score"};

        Object[][] data = new Object[User.getUsersSorted().size()][3];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = setAvatar(User.getUsersSorted().get(i));
            data[i][1] = User.getUsersSorted().get(i).getUsername();
            data[i][2] = User.getUsersSorted().get(i).getHighScore();
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane);

        setSize(300, 200);
        setVisible(true);
    }

    public Image setAvatar(User user) {
//        Image image = new Image(ProfileMenu.class.getResource("/com/ap/stronghold/Media/Avatars/"+ user.getAvatarNumber()+ ".png").toExternalForm());
   return null;
    }
}