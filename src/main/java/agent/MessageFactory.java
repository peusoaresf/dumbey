package agent;

import java.util.List;

import com.openai.models.chat.completions.ChatCompletionAssistantMessageParam;
import com.openai.models.chat.completions.ChatCompletionMessageToolCall;
import com.openai.models.chat.completions.ChatCompletionToolMessageParam;
import com.openai.models.chat.completions.ChatCompletionUserMessageParam;

public class MessageFactory {
    public static ChatCompletionUserMessageParam user(String prompt) {
        return ChatCompletionUserMessageParam.builder()
            .content(prompt)
            .build();
    }

    public static ChatCompletionAssistantMessageParam assistant(List<ChatCompletionMessageToolCall> toolCalls) {
        return ChatCompletionAssistantMessageParam.builder()
            .toolCalls(toolCalls)
            .build();
    }

    public static ChatCompletionToolMessageParam tool(String toolCallId, String callResult) {
        return ChatCompletionToolMessageParam.builder()
            .toolCallId(toolCallId)
            .content(callResult)
            .build();
    }
}
