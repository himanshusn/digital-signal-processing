package cps.lab.gui.menu;

import cps.lab.gui.windows.FilterCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: maciek
 * Date: 13.11.13
 * Time: 23:15
 */
public class FilteringMenuItem extends JMenuItem implements ActionListener {

    public FilteringMenuItem(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame filter = new FilterCreator();
        filter.setVisible(true);

    }
}
