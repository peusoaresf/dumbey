package gui;

import javax.swing.*;
import java.awt.*;

public abstract class ModelIcon implements Icon {
    @Override
    public int getIconWidth() {
        return 48;
    }

    @Override
    public int getIconHeight() {
        return 48;
    }

    protected BasicStroke getStroke() {
        return new BasicStroke(4);
    }

    public static class Missing extends ModelIcon {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(Color.WHITE);
            g2d.fillRect(x + 1, y + 1, getIconWidth() - 2, getIconHeight() - 2);

            g2d.setColor(Color.BLACK);
            g2d.drawRect(x + 1, y + 1, getIconWidth() - 2, getIconHeight() - 2);

            g2d.setColor(Color.RED);

            g2d.setStroke(getStroke());
            g2d.drawLine(x + 10, y + 10, x + getIconWidth() - 10, y + getIconHeight() - 10);
            g2d.drawLine(x + 10, y + getIconHeight() - 10, x + getIconWidth() - 10, y + 10);

            g2d.dispose();
        }
    }
}
