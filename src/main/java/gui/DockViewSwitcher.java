package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class DockViewSwitcher extends JPanel {
    private final List<SwitcherSlot> slots = Arrays.asList(
        new SwitcherSlot(new LatchingButton("Progress"), new ProgressTracker()),
        new SwitcherSlot(new LatchingButton("Models"), new ModelInventory())
    );

    private final NavigationBar navigationBar = new NavigationBar(getButtons());
    private final DockViewDeck dockViewDeck = new DockViewDeck(getViews());

    DockViewSwitcher() {
        this.setLayout(new BorderLayout(0, 8));

        for (SwitcherSlot slot : slots) {
            slot.onClicked(() -> handleSlotClick(slot));
        }

        add(navigationBar, BorderLayout.NORTH);
        add(dockViewDeck, BorderLayout.CENTER);

        handleSlotClick(slots.getFirst());
    }

    private void handleSlotClick(SwitcherSlot slot) {
        navigationBar.setSelected(slot.button);
        dockViewDeck.show(slot.view);
    }

    private List<AbstractButton> getButtons() {
        return slots.stream().map(s -> s.button).toList();
    }

    private List<DockView> getViews() {
        return slots.stream().map(s -> s.view).toList();
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
