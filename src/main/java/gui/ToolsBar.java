package gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.Border;

import tools.base.ToolSlot;
import tools.base.ToolsRegistry;

public class ToolsBar extends JPanel {
    private final GridBagConstraints constraints = new GridBagConstraints();

    public ToolsBar() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(createBorder());
        this.setConstraints();

        for (ToolSlot slot : ToolsRegistry.getAllSlots()) {
            var toolToggle = new LatchButton(slot.getToolName());

            toolToggle.setSelected(slot.isEnabled());
            toolToggle.addActionListener(_ -> slot.setEnabled(toolToggle.isSelected()));

            this.add(toolToggle);
        }
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }

    private void setConstraints() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(1, 1, 1, 1);
    }

    private Border createBorder() {
        var titledBorder = BorderFactory.createTitledBorder("Tools");
        titledBorder.setBorder(BorderFactory.createEmptyBorder());

        return BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            titledBorder
        );
    }

    private static class LatchButton extends JCheckBox {
        public LatchButton(String title) {
            super(title);
        }
    }
}
