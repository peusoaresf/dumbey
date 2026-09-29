package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class SidePanel extends JPanel {
    public SidePanel() {
        this.setLayout(new GridBagLayout());

        this.add(new ModelSlot(new MissingIcon()), getModelSlotConstraints());

        var swappablePanels = new SwappablePanelContainer();

        this.add(new ButtonsRow(swappablePanels.getButtons()), getButtonsRowConstraints());

        for (InfoContainer infoContainer : swappablePanels.getInfoContainers()) {
            this.add(infoContainer, getInfoContainerConstraints());
        }
    }

    private GridBagConstraints getModelSlotConstraints() {
        var constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(24, 0, 24, 0);
        return constraints;
    }

    private GridBagConstraints getButtonsRowConstraints() {
        var constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        return constraints;
    }

    private GridBagConstraints getInfoContainerConstraints() {
        var constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(8, 8, 8, 8);
        return constraints;
    }

    private static class ModelSlot extends JLabel {
        ModelSlot(Icon icon) {
            TitledBorder border = BorderFactory.createTitledBorder("Model");
            border.setBorder(BorderFactory.createLoweredBevelBorder());
            border.setTitleFont(border.getTitleFont().deriveFont(8f));
            border.setTitleJustification(TitledBorder.CENTER);

            this.setBorder(border);
            this.setIcon(icon);
        }
    }

    private static class MissingIcon implements Icon {
        private final int width = 48;
        private final int height = 48;

        private final BasicStroke stroke = new BasicStroke(4);

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(Color.WHITE);
            g2d.fillRect(x +1 ,y + 1,width -2 ,height -2);

            g2d.setColor(Color.BLACK);
            g2d.drawRect(x +1 ,y + 1,width -2 ,height -2);

            g2d.setColor(Color.RED);

            g2d.setStroke(stroke);
            g2d.drawLine(x +10, y + 10, x + width -10, y + height -10);
            g2d.drawLine(x +10, y + height -10, x + width -10, y + 10);

            g2d.dispose();
        }

        public int getIconWidth() {
            return width;
        }

        public int getIconHeight() {
            return height;
        }
    }

    private static class ButtonsRow extends JPanel {
        ButtonsRow(List<AbstractButton> buttons) {
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 0));

            for (AbstractButton button : buttons) {
                this.add(button);
            }
        }
    }

    private static class SwappablePanelContainer {
        private final List<SwappableSlot> slots = Arrays.asList(
            new SwappableSlot(new LatchingButton("Progress"), new ProgressTracker()),
            new SwappableSlot(new LatchingButton("Models"), new ModelInventory())
        );

        SwappablePanelContainer() {
            for (SwappableSlot slot : slots) {
                slot.onClicked(() -> handleSlotClicked(slot));
            }

            handleSlotClicked(slots.getFirst());
        }

        public List<AbstractButton> getButtons() {
            return slots.stream().map(slot -> slot.button).toList();
        }

        public List<InfoContainer> getInfoContainers() {
            return slots.stream().map(slot -> slot.container).toList();
        }

        private void handleSlotClicked(SwappableSlot targetSlot) {
            for (SwappableSlot slot : slots) {
                slot.hide();
            }

            targetSlot.show();
        }

        private record SwappableSlot(
            AbstractButton button,
            InfoContainer container
        ) {
            public void onClicked(Runnable handler) {
                this.button.addActionListener(_ -> handler.run());
            }

            public void show() {
                this.setActive(true);
            }

            public void hide() {
                this.setActive(false);
            }

            private void setActive(boolean isActive) {
                this.button.setSelected(isActive);
                this.container.setVisible(isActive);
            }
        }
    }

    // TODO: I dont like this name
    private static class InfoContainer extends JPanel {
        InfoContainer() {
            this.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createRaisedBevelBorder(),
                    BorderFactory.createLoweredBevelBorder()
                )
            );
        }
    }

    private static class ProgressTracker extends InfoContainer {
        ProgressTracker() {
            super();
            this.add(new JLabel("Progress Tracker"));
        }
    }

    private static class ModelInventory extends InfoContainer {
        ModelInventory() {
            super();
            this.add(new JLabel("Model Inventory"));
        }
    }
}
