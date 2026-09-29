package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DockViewDeck extends JPanel {
    private final CardLayout cardLayout = new CardLayout();

    public DockViewDeck(List<DockView> views) {
        setLayout(cardLayout);

        for (DockView view : views) {
            add(view, view.getIdentifier());
        }
    }

    public void show(DockView view) {
        cardLayout.show(this, view.getIdentifier());
    }
}
