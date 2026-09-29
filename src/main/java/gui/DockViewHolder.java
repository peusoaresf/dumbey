package gui;

import javax.swing.*;
import java.awt.*;

public class DockViewHolder extends JPanel {
    private final DockView progressTracker = new ProgressTracker();
    private final DockView modelInventory = new ModelInventory();

    private final CardLayout cardLayout = new CardLayout();

    DockViewHolder() {
        setLayout(cardLayout);

        add(progressTracker, progressTracker.getName());
        add(modelInventory, modelInventory.getName());
    }

    public void show(DockView view) {
        cardLayout.show(this, view.getName());
    }

    public DockView getProgressTracker() {
        return progressTracker;
    }

    public DockView getModelInventory() {
        return modelInventory;
    }
}
