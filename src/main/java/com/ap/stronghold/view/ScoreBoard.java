package com.ap.stronghold.view;

import javax.swing.*;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.ap.stronghold.controller.ProfileMenuController;
import com.ap.stronghold.model.User;

public class ScoreBoard extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;

    public ScoreBoard() throws Exception {
        setTitle("score board");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        String[] columnNames = {"avatar","username", "score"};
        table = new JTable();
        Object[][] data = new Object[User.getUsersSorted().size()][3];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = new File(User.getUsersSorted().get(i).getAvatarPath());

            data[i][1] = User.getUsersSorted().get(i).getUsername();
            data[i][2] = User.getUsersSorted().get(i).getHighScore();
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row,int column) {
                return false;
            }
        };

        table.setModel(model);
        table.getColumnModel().getColumn(0).setCellRenderer(new ImageTableCellRenderer());
        table.setDefaultRenderer(Object.class,new NonEditableCellRenderer());
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//        Font font = centerRenderer.getFont();
//        centerRenderer.setFont(font.deriveFont(font.getSize() + 5f));
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//        }
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    int column = table.getSelectedColumn();
                    if (column == 0 ) {
                        File object = (File) table.getValueAt(row, column);
                        ProfileMenuController.setPhoto(object.getPath());
                        JOptionPane.showMessageDialog(null, "new avatar for you has been set");
                    }
                }
            }
        });
        table.setRowHeight(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

