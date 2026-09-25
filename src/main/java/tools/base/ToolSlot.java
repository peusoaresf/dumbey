package tools.base;

public class ToolSlot {
    private boolean enabled = true;
    private final Class<? extends Tool> tool;

    ToolSlot(Class<? extends Tool> tool) {
        this.tool = tool;
    }

    public Class<? extends Tool> getTool() {
        return tool;
    }

    public String getToolName() {
        return tool.getSimpleName();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
