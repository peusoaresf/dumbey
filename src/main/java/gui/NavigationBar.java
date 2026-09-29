package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NavigationBar extends JPanel {
    private final ButtonGroup group = new ButtonGroup();

    public NavigationBar(List<AbstractButton> buttons) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 0));

        for (AbstractButton button : buttons) {
            group.add(button);
            add(button);
        }
    }

    public void setSelected(AbstractButton button) {
        button.setSelected(true);
    }
}
