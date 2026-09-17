package gui;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

import javax.swing.JFrame;

public class GUI extends JFrame {

    private final ChatContainer chatContainer = new ChatContainer();
    private final InputContainer inputContainer = new InputContainer();

    public GUI() {
        super("My app");

        this.setSize(640, 480);
        this.setLayout(new GridLayout(2, 1));

        this.add(chatContainer);
        this.add(inputContainer);

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
        inputContainer.setProcessing(isProcessing);
    }

    public void addError(String error) {
        this.chatContainer.addError(error);
    }

    public void addAgentReply(String reply) {
        this.chatContainer.addAgentReply(reply);
    }

    public void setOnPromptSubmitted(Consumer<String> handler) {
        inputContainer.setOnPromptSubmitted(prompt -> {
            chatContainer.addUserPrompt(prompt);

            handler.accept(prompt);
        });
    }
}
