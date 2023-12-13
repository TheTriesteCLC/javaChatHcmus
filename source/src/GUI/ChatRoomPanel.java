package GUI;

import Data.ChatRoom;
import Data.Message;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class ChatRoomPanel extends JPanel {
    private String client;
    private ChatRoom room;
    private DefaultListModel<String> messListModel;
    public ChatRoomPanel(ChatRoom room) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.WHITE);

        List<Message> messages= room.getMessages();
        this.messListModel = new DefaultListModel<String>();
        for(Message mess : messages) {
            String displayMess = String.format("%s : %s",
                    (Objects.equals(mess.getSender(), client) ? "You" : mess.getSender()),mess.getContent());
            this.messListModel.addElement(displayMess);
        }

        JScrollPane messagesScroll = new JScrollPane();
        messagesScroll.setMaximumSize(new Dimension(200, 200));
        messagesScroll.setMinimumSize (new Dimension (200,200));
    }
}
