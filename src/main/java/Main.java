import java.util.concurrent.ExecutionException;

import agent.Agent;
import gui.GUI;

import javax.swing.*;

void main() {
    var gui = new GUI();

    var agent = new Agent(
        System.getenv("BASE_URL"), 
        System.getenv("MODEL_NAME"), 
        System.getenv("API_KEY")
    );

    gui.setOnPromptSubmitted(prompt -> (new SwingWorker<String, Void>() {
        @Override
        protected String doInBackground() {
            agent.setToolCallSubscriber(gui::addAgentReply); // TODO: no idea why I setting this outside worker makes UI behave weirdly

            gui.setProcessing(true);
            return agent.prompt(prompt);
        }

        @Override
        public void done() {
            gui.setProcessing(false);

            try {
                gui.addAgentReply(get());
            } catch (InterruptedException | ExecutionException e) {
                gui.addError(e.getMessage());
            }
        }
    }).execute());
}
