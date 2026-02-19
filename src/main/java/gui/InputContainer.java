package gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.function.Consumer;

public class InputContainer extends JPanel {
    private boolean isProcessing = false;

    private final JTextArea textArea = new JTextArea(10, 50);
    private final JButton button = new JButton("Submit");

    public InputContainer() {
        button.setSize(100, 50);
        button.setEnabled(false);

        this.add(textArea);
        this.add(button);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handler();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handler();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handler();
            }

            private void handler() {
                if (isProcessing) {
                    button.setEnabled(false);
                    return;
                }

                button.setEnabled(textArea.getText() != null && !textArea.getText().isEmpty() && !textArea.getText().isBlank());
            }
        });
    }

    public void setProcessing(boolean isProcessing) {
        this.isProcessing = isProcessing;

        if (isProcessing) {
            button.setText("Processing...");
            return;
        }

        button.setText("Submit");
    }

    public void setOnPromptSubmitted(Consumer<String> handler) {
        button.addActionListener(_ -> {
            String prompt = textArea.getText();
            textArea.setText("");
            handler.accept(prompt);
        });
    }
}
