package gui;

import javax.swing.*;

public abstract class DockView extends JPanel {
    public DockView() {
        setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()
            )
        );
    }

    public String getIdentifier() {
        return getClass().getSimpleName();
    }
}
