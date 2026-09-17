package tools;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import tools.base.Tool;

@JsonClassDescription("Execute a shell command")
public class RunBashCommand implements Tool {

    @JsonPropertyDescription("The command to execute")
    public String command;

    @Override
    public String execute() {
        try {
            Process process = new ProcessBuilder("/bin/sh", "-c", command)
                .redirectErrorStream(true)
                .start();

            process.waitFor();

            try (BufferedReader reader = process.inputReader()) {
                return String.join("\n", reader.readAllLines());
            }
        } catch (IOException | InterruptedException e) {
            return "Fatal exception: " + e.getMessage();
        }
    }
}
