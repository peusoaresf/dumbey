package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ModelSlot extends JLabel {
    public ModelSlot(ModelIcon icon) {
        TitledBorder border = BorderFactory.createTitledBorder("Model");
        border.setBorder(BorderFactory.createLoweredBevelBorder());
        border.setTitleFont(border.getTitleFont().deriveFont(8f));
        border.setTitleJustification(TitledBorder.CENTER);

        this.setBorder(border);
        this.setIcon(icon);
    }
}
