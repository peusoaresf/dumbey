package gui;

import java.awt.*;

import javax.swing.*;

public class ChatContainer extends JScrollPane {
    private final MessagesPanel messages = new MessagesPanel();

    public ChatContainer() {
        super();

        this.setViewportView(messages);
    }

    public void addError(String error) {
        addMessage(new ErrorMessage(error));
    }

    public void addAgentReply(String reply) {
        addMessage(new AgentMessage(reply));
    }

    public void addUserPrompt(String prompt) {
        addMessage(new UserMessage(prompt));
    }

    private void addMessage(MessageText message) {
        messages.add(message);
        scrollToBottom();
    }

    private void scrollToBottom() {
        this.validate();
        SwingUtilities.invokeLater(() -> {
            this.getVerticalScrollBar().setValue(this.getVerticalScrollBar().getMaximum());
        });
    }

    private static class MessageText extends JTextArea {
        public MessageText(String message) {
            super(message);
            this.setEditable(false);
            this.setWrapStyleWord(true);
            this.setLineWrap(true);
            this.setBorder(BorderFactory.createEmptyBorder(0, 0, 32, 0));
        }
    }

    private static class AgentMessage extends MessageText {
        public AgentMessage(String message) {
            super(message);
            this.setForeground(Color.BLACK);
        }
    }

    private static class ErrorMessage extends MessageText {
        public ErrorMessage(String message) {
            super(message);
            this.setForeground(Color.RED);
        }
    }

    private static class UserMessage extends MessageText {
        public UserMessage(String message) {
            super(message);
            this.setForeground(Color.GRAY);
            this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
    }

    private static class MessagesPanel extends JPanel implements Scrollable {
        public MessagesPanel() {
            setBackground(Color.WHITE);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            add(new MessageText("Hello, I'm an AI agent. How can I assist you?"));
        }

        @Override
        public Dimension getPreferredScrollableViewportSize() {
            return getPreferredSize();
        }

        @Override
        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 16;
        }

        @Override
        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
            return visibleRect.height;
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return true;
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return false;
        }
    }
}
