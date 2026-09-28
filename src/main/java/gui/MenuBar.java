package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        var menu = new JMenu("Help");

        menu.add(new AboutItem());

        this.add(menu);
    }

    private static class AboutItem extends JMenuItem implements ActionListener {
        AboutItem() {
            super("About");

            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(
                this,
                "Dumbey Alpha\n2026",
                "About",
                JOptionPane.PLAIN_MESSAGE
            );
        }
    }
}
