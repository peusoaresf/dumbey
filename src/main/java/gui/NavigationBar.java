package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class NavigationBar extends JPanel {
    private final LatchingButton progressButton = new LatchingButton("Progress");
    private final LatchingButton modelsButton = new LatchingButton("Models");

    private final ButtonGroup group = new ButtonGroup() {{
        add(progressButton);
        add(modelsButton);
    }};

    public NavigationBar() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 0));

        for (AbstractButton button : Collections.list(group.getElements())) {
            add(button);
        }
    }

    public LatchingButton getModelsButton() {
        return modelsButton;
    }

    public LatchingButton getProgressButton() {
        return progressButton;
    }
}
