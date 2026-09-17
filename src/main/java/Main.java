import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import agent.Agent;
import gui.GUI;

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
            agent.setToolCallSubscriber(msg -> SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gui.addAgentReply(msg);
                }
            }));

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
