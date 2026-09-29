package gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class GUI extends JFrame {

    private final ChatTranscript chatTranscript = new ChatTranscript();
    private final PromptComposer promptComposer = new PromptComposer();
    private final Dock dock = new Dock();

    public GUI() {
        super("Dumbey");

        this.setSize(1024, 768);

        this.add(
            mainView(
                chatTranscript,
                promptComposer,
                dock
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

        setDividerToWhite(verticalContainer);
        verticalContainer.setOneTouchExpandable(true);
        verticalContainer.setMinimumSize(new Dimension(0,0));
        verticalContainer.setDividerLocation((int)(this.getHeight() * 0.6));
        verticalContainer.setResizeWeight(0.6);

        var horizontalContainer = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            verticalContainer,
            sideSlot
        );

        setDividerToWhite(horizontalContainer);
        horizontalContainer.setOneTouchExpandable(true);
        horizontalContainer.setDividerLocation((int)(this.getWidth() * 0.75));
        horizontalContainer.setResizeWeight(0.75);

        return horizontalContainer;
    }

    private void setDividerToWhite(JSplitPane pane) {
        var customUI = new BasicSplitPaneUI() {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    public void setBorder(Border b) {}

                    @Override
                    public void paint(Graphics g) {
                        g.setColor(Color.WHITE);
                        g.fillRect(0, 0, getSize().width, getSize().height);
                        super.paint(g);
                    }
                };
            }
        };

        pane.setUI(customUI);
    }

    public void setProcessing(boolean isProcessing) {
        promptComposer.setProcessing(isProcessing);
    }

    public void addError(String error) {
        this.chatTranscript.addError(error);
    }

    public void addAgentReply(String reply) {
        this.chatTranscript.addAgentReply(reply);
    }

    public void setOnPromptSubmitted(Consumer<String> handler) {
        promptComposer.setOnPromptSubmitted(prompt -> {
            chatTranscript.addUserPrompt(prompt);

            handler.accept(prompt);
        });
    }
}
