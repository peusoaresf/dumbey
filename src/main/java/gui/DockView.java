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

    // TODO: need a new name, this conflicts with swing
    public String getName() {
        return getClass().getSimpleName();
    }
}
