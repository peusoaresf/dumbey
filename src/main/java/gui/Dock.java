package gui;

import javax.swing.*;
import java.awt.*;

public class Dock extends JPanel {
    public Dock() {
        this.setLayout(new GridBagLayout());

        this.add(new ModelSlot(new ModelIcon.Missing()), getModelSlotConstraints());
        this.add(new DockViewSwitcher(), getDockViewSwitcherConstraints());
    }

    private GridBagConstraints getModelSlotConstraints() {
        var constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(24, 0, 24, 0);
        return constraints;
    }

    private GridBagConstraints getDockViewSwitcherConstraints() {
        var constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(0, 8, 8, 8);
        return constraints;
    }
}
