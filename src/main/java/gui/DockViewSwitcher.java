package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class DockViewSwitcher extends JPanel {
    private final NavigationBar navigationBar = new NavigationBar();
    private final DockViewHolder dockViewHolder = new DockViewHolder();

    private final List<SwitcherSlot> slots = Arrays.asList(
        new SwitcherSlot(navigationBar.getProgressButton(), dockViewHolder.getProgressTracker()),
        new SwitcherSlot(navigationBar.getModelsButton(), dockViewHolder.getModelInventory())
    );

    DockViewSwitcher() {
        this.setLayout(new BorderLayout(0, 8));

        add(navigationBar, BorderLayout.NORTH);
        add(dockViewHolder, BorderLayout.CENTER);

        for (SwitcherSlot slot : slots) {
            slot.onClicked(() -> handleSlotClick(slot));
        }

        handleSlotClick(slots.getFirst());
    }

    private void handleSlotClick(SwitcherSlot slot) {
        slot.button.setSelected(true);
        dockViewHolder.show(slot.view);
    }

    private record SwitcherSlot(
        AbstractButton button,
        DockView view
    ) {
        public void onClicked(Runnable handler) {
            button.addActionListener(_ -> handler.run());
        }
    }
}
