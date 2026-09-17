package tools.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tools.ReadFile;
import tools.RunBashCommand;
import tools.WriteFile;

public class ToolsRegistry {
    private static final Map<String, Class<? extends Tool>> registry = new HashMap<>() {{
        put(ReadFile.class.getSimpleName(), ReadFile.class);
        put(WriteFile.class.getSimpleName(), WriteFile.class);
        put(RunBashCommand.class.getSimpleName(), RunBashCommand.class);
    }};

    public static Class<Tool> get(String toolName) {
        if (!registry.containsKey(toolName)) {
            throw new IllegalArgumentException("Unknown tool: " + toolName);
        }

        return (Class<Tool>) registry.get(toolName);
    }

    public static Collection<Class<? extends Tool>> getAll() {
        return registry.values();
    }
}
