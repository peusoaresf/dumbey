package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PromptContainer extends JPanel {
    private boolean isProcessing = false;

    private final PromptArea textArea = new PromptArea();
    private final SubmitButton button = new SubmitButton();

    public PromptContainer() {
        this.setLayout(new GridBagLayout());

        button.setEnabled(false);

        this.add(textArea, textArea.getConstraints());
        this.add(button, button.getConstraints());

        textArea.setOnValueChanged(() -> {
            if (isProcessing) {
                button.setEnabled(false);
                return;
            }

            button.setEnabled(textArea.isFilled());
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

    private static class PromptArea extends JTextArea {
        private final GridBagConstraints constraints = new GridBagConstraints();

        public PromptArea() {
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.insets = new Insets(20, 20, 20, 10);
        }

        public boolean isFilled() {
            return getText() != null && !getText().isEmpty() && !getText().isBlank();
        }

        public void setOnValueChanged(Runnable handler) {
            this.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    handler.run();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    handler.run();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    handler.run();
                }
            });
        }

        public GridBagConstraints getConstraints() {
            return this.constraints;
        }
    }

    private static class SubmitButton extends JButton {
        private final GridBagConstraints constraints = new GridBagConstraints();

        public SubmitButton() {
            this.setText("Submit");

            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.ipady = 0;
            constraints.insets = new Insets(0, 0, 20, 0);
        }

        public GridBagConstraints getConstraints() {
            return this.constraints;
        }
    }
}
