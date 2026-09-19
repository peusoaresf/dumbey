package utils;

public class Logger {
    private final Level level = Level.DEBUG;

    public void debug(String message) {
        if (level != Level.DEBUG) {
            return;
        }

        IO.println(message);
    }

    public void info(String message) {
        IO.println(message);
    }

    enum Level { DEBUG, INFO }
}
