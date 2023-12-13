package Data;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private String sender;
    private String content;
    private String receiver;
    public Message(String sender, String content, String receiver) {
        this.sender = sender;
        this.content = content;
        this.receiver = receiver;
    }
    public String getSender() {
        return this.sender;
    }
    public String getContent() {
        return this.content;
    }

    public String getReceiver() {
        return receiver;
    }
}
