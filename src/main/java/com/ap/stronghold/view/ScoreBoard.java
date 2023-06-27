package com.ap.stronghold.view;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.model.User;

public class ScoreBoard extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;

    public ScoreBoard() throws Exception {
        setTitle("score board");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        String[] columnNames = {"avatar","username", "score"};

        Object[][] data = new Object[User.getUsersSorted().size()][3];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = ProfileMenu.setAvatar(User.getUsersSorted().get(i));
            data[i][1] = User.getUsersSorted().get(i).getUsername();
            data[i][2] = User.getUsersSorted().get(i).getHighScore();
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        table.setValueAt(ProfileMenu.setAvatar(User.getUsersSorted().get(0)),0,1);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    Object object = (ImageView) target.getValueAt(row, 0);
                    Controller.setNewAvatar(object);
                    JOptionPane.showMessageDialog(null, "new avatar for you has been set");
                }
            }
        });

        setSize(300, 200);
        setVisible(true);
    }


}