package gui;

import javax.swing.*;

public class ChatContainer extends JScrollPane {
    private final DefaultListModel<String> messages = new DefaultListModel<>() {{
        addElement("Agent: Hello, I'm an AI agent. How can I assist you?");
    }};

    public ChatContainer() {
        super();

        this.setViewportView(new JList<>(messages));
    }

    public void addError(String error) {
        addMessage("Error", error);
    }

    public void addAgentReply(String reply) {
        addMessage("Agent", reply);
    }

    public void addUserPrompt(String prompt) {
        addMessage("You", prompt);
    }

    private void addMessage(String actor, String message) {
        messages.addElement(" ");
        messages.addElement(actor + ": " + message);
        this.getVerticalScrollBar().setValue(this.getVerticalScrollBar().getMaximum());
    }
}
