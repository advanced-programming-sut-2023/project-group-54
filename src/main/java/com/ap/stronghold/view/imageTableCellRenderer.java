package com.ap.stronghold.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class imageTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof File) {
            //String string = ((File) value).getPath().replaceAll("^file:\\\\","");
            File file = (File) value;
            try {
                Image image = ImageIO.read(file);
                ImageIcon icon = new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                setIcon(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            setIcon(null);
        }

        return this;
    }
}