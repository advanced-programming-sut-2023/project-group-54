package com.ap.stronghold.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class NonEditableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //table.setEnabled(false);
        if (isSelected) {
            component.setBackground(table.getSelectionBackground());
            component.setForeground(table.getSelectionForeground());
        } else {
            component.setBackground(table.getBackground());
            component.setForeground(table.getForeground());
        }
        return component;
    }
}