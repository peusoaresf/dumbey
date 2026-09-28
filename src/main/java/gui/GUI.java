package gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

import javax.swing.*;

public class GUI extends JFrame {

    private final MessagesContainer messagesContainer = new MessagesContainer();
    private final PromptContainer promptContainer = new PromptContainer();

    public GUI() {
        super("Dumbey");

        this.setSize(1024, 768);

        this.add(
            mainView(
                messagesContainer,
                promptContainer,
                new JPanel()
            )
        );

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        this.setVisible(true);
    }

    private Component mainView(Component mainSlot, Component bottomSlot, Component sideSlot) {
        var verticalContainer = new JSplitPane(
            JSplitPane.VERTICAL_SPLIT,
            mainSlot,
            bottomSlot
        );

        verticalContainer.setOneTouchExpandable(true);
        verticalContainer.setMinimumSize(new Dimension(0,0));
        verticalContainer.setDividerLocation((int)(this.getHeight() * 0.6));

        var horizontalContainer = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            verticalContainer,
            sideSlot
        );

        horizontalContainer.setOneTouchExpandable(true);
        horizontalContainer.setDividerLocation((int)(this.getWidth() * 0.75));

        return horizontalContainer;
    }

    public void setProcessing(boolean isProcessing) {
        promptContainer.setProcessing(isProcessing);
    }

    public void addError(String error) {
        this.messagesContainer.addError(error);
    }

    public void addAgentReply(String reply) {
        this.messagesContainer.addAgentReply(reply);
    }

    public void setOnPromptSubmitted(Consumer<String> handler) {
        promptContainer.setOnPromptSubmitted(prompt -> {
            messagesContainer.addUserPrompt(prompt);

            handler.accept(prompt);
        });
    }
}
