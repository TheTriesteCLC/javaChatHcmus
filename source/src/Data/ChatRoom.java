package Data;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private String roomname;
    private String type;
    private List<Message> messages;
    private List<String> clients;
    public ChatRoom(String roomname, String type ,List<String> clients) {
        this.roomname = roomname;
        this.type = type;
        this.messages = new ArrayList<Message>();
        this.clients = clients;
    }
    public ChatRoom(String roomname, String type, List<Message> messages ,List<String> clients) {
        this.roomname = roomname;
        this.type = type;
        this.messages = messages;
        this.clients = clients;
    }
    public void addMessage(Message newMess) {
        this.messages.add(newMess);
    }

    public String getRoomname() {
        return roomname;
    }

    public String getType() {
        return type;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<String> getClients() {
        return clients;
    }
}
