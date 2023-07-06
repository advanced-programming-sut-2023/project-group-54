package com.ap.stronghold.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.model.chat.Group;
import com.ap.stronghold.model.chat.PrivateChat;
import com.ap.stronghold.model.chat.PublicChat;

public class ChatList extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;

    public ChatList() throws Exception {
        setTitle("chat list");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        String[] columnNames = {"chat id","chat name","chat type", "last message time"};
        table = new JTable();
        String[][] data = new String[Controller.getLoggedInUser().getAllChat().getNumberOfChat()][4];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = Controller.getLoggedInUser().getAllChat().getAllChatsOfUser().get(i).getId();
            data[i][1] = Controller.getLoggedInUser().getAllChat().getAllChatsOfUser().get(i).getName();
            String chatType = "";
            if (Controller.getLoggedInUser().getAllChat().getAllChatsOfUser().get(i) instanceof PublicChat)
                chatType = "public chat";
            else if (Controller.getLoggedInUser().getAllChat().getAllChatsOfUser().get(i) instanceof PrivateChat)
                chatType = "private chat";
            else if (Controller.getLoggedInUser().getAllChat().getAllChatsOfUser().get(i) instanceof Group)
                chatType = "group chat";
            data[i][2] = chatType;
            data[i][3] = Controller.getLoggedInUser().getAllChat().getAllChatsOfUser().get(i).getMessages()
                    .get(Controller.getLoggedInUser().getAllChat().getAllChatsOfUser().get(i).getMessages().size()-1).getMessageTime();

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
                    //int column = table.getSelectedColumn();
                    JOptionPane.showMessageDialog(null, "continue chat in command line");
                    setVisible(false);
                    try {
                        ChatMenu chatMenu = new ChatMenu(Controller.getLoggedInUser().getAllChat().findChatsOfUser((String)table.getValueAt(row,0)),
                                InetAddress.getLocalHost().getHostAddress(), 8080);
                        chatMenu.start();
                        chatMenu.join();
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

//                    if (column == 0 ) {
//                        File object = (File) table.getValueAt(row, column);
//                        ProfileMenuController.setPhoto(object.getPath());
//                        JOptionPane.showMessageDialog(null, "new avatar for you has been set");
//                    }


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




