package tools.base;

import java.util.*;
import java.util.stream.Collectors;

import tools.ReadFile;
import tools.RunBashCommand;
import tools.WriteFile;

public class ToolsRegistry {
    private static final Map<String, ToolSlot> registry = new HashMap<>() {{
        put(ReadFile.class.getSimpleName(), new ToolSlot(ReadFile.class));
        put(WriteFile.class.getSimpleName(), new ToolSlot(WriteFile.class));
        put(RunBashCommand.class.getSimpleName(), new ToolSlot(RunBashCommand.class));
    }};

    public static Class<? extends Tool> getTool(String toolName) {
        if (!registry.containsKey(toolName)) {
            throw new IllegalArgumentException("Unknown tool: " + toolName);
        }

        if (!registry.get(toolName).isEnabled()) {
            throw new RuntimeException("Tool is disabled: " + toolName);
        }

        return registry.get(toolName).getTool();
    }

    public static Collection<ToolSlot> getAllSlots() {
        return registry.values();
    }

    public static Collection<ToolSlot> getActiveSlots() {
        return getAllSlots().stream()
            .filter(ToolSlot::isEnabled)
            .toList();
    }
}
