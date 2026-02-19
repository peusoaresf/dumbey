package utils;

public class Logger {
    private final Level level = Level.DEBUG;

    public void debug(String message) {
        if (level != Level.DEBUG) {
            return;
        }

        System.out.println(message);
    }

    public void info(String message) {
        System.out.println(message);
    }

    enum Level { DEBUG, INFO }
}
