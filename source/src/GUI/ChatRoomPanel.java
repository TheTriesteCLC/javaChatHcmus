package GUI;

import Data.ChatRoom;
import Data.Client;
import Data.Message;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class ChatRoomPanel extends JPanel {
    private Client currClient;
    private ChatRoom room;
    private DefaultListModel<String> messListModel;
    private JList messList;
    public ChatRoomPanel(Client client, ChatRoom room) {
        this.currClient = client;
        this.room = room;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.WHITE);

        List<Message> messages= this.room.getMessages();
        this.messListModel = new DefaultListModel<String>();
        for(Message mess : messages) {
            String displayMess = String.format("%s : %s",
                    (Objects.equals(mess.getSender(), currClient.getUsername()) ? "You" : mess.getSender()),mess.getContent());
            this.messListModel.addElement(displayMess);
        }

        this.messList = new JList<>(this.messListModel);
        messList.setFont(new Font("Consolas", Font.BOLD, 20));
        JScrollPane messagesScroll = new JScrollPane(this.messList);

        this.add(messagesScroll);
    }
}
