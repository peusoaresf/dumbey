package gui;

import javax.swing.*;

public class LatchingButton extends JToggleButton {
    LatchingButton(String title) {
        super(title);

        setContentAreaFilled(false);
        setFocusPainted(false);

        updateBorder();

        addItemListener(_ -> updateBorder());
    }

    private void updateBorder() {
        var bevel = isSelected()
            ? BorderFactory.createLoweredBevelBorder()
            : BorderFactory.createRaisedBevelBorder();

        setBorder(
            BorderFactory.createCompoundBorder(
                bevel,
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            )
        );
    }
}
