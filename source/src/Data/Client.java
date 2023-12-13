package Data;

import java.util.List;

public class Client {
    private String username;
    private List<ChatRoom> chatRooms;
    private List<List<Message>> allMessages;
    public Client(String username, List<ChatRoom> chatRooms, List<List<Message>> allMessages) {
        this.username = username;
        this.chatRooms = chatRooms;
        this.allMessages = allMessages;
    }

    public String getUsername() {
        return username;
    }

    public List<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public List<List<Message>> getAllMessages() {
        return allMessages;
    }
}
