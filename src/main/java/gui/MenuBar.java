package gui;

import tools.base.ToolSlot;
import tools.base.ToolsRegistry;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        var menu = new JMenu("Tools");

        for (ToolSlot slot : ToolsRegistry.getAllSlots()) {
            var menuItem = new JCheckBoxMenuItem(slot.getToolName(), slot.isEnabled());

            menuItem.addActionListener(e -> slot.setEnabled(menuItem.isSelected()));

            menu.add(menuItem);
        }

        this.add(menu);
    }
}
