# Dumbey

Repo born from the ["Build Your own Claude Code"](https://codecrafters.io/challenges/claude-code) CodeCrafters challenge, Dumbey is a Java harness for LLM models... a not-so-bright one.

Eventually, after getting to the end of the challenge and having the agent do some basic things over CLI commands, I decided (for some god knows why reason) to add a Swing UI interface to it (maybe I felt nostalgic for having worked with it back in 2015-2017).

## Dependencies

- [Make](https://www.gnu.org/software/make/)
- [Java SDK (v25)](https://sdkman.io/)

## Quick Start

1. Create a copy of `.env.example` and call it `.env`

2. Start LM Studio under `http://localhost:1234/v1` and load the `gemma-4-e2b` model

3. Run `make run`

4. Play around with.. an LLM agent of sorts!

## Shortcomings / TODOs

1. For some reason, after plugging the gemma model, a simple 'Hi' prompt is enough to send the agent in a loop that never ends with a proper reply (looking at the logs it doesnt seem a problem in the model reasoning, more in a 'code detecting the end' problem);

2. I realized swing is really not-so-great and started to build out some cli tools back again, the main class flow could be smth like:

```java
var parameters = new ProgramParameters(args);

var agent = new Agent(parameters.getBaseUrl(), parameters.getModel(), parameters.getApiKey());

String reply = agent.prompt(parameters.getPrompt());

logger.info(reply);
```

and it would be called like:

```
java -jar target/dumbey.jar -m "gemma-4-e2b" -p "how many tools do you have available?"
```

But tbh I never gave it too much attention (moved over to a terminalUI based agent called `clam`)... so, documenting this just in case, but might as well enjoy the weird fact of having a Swing Agent.