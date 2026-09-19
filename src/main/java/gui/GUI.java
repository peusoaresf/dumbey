package gui;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

import javax.swing.JFrame;

public class GUI extends JFrame {

    private final MessagesContainer messagesContainer = new MessagesContainer();
    private final PromptContainer promptContainer = new PromptContainer();

    public GUI() {
        super("My app");

        this.setSize(640, 480);
        this.setLayout(new GridLayout(2, 1));

        this.add(messagesContainer);
        this.add(promptContainer);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        this.setVisible(true);
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
