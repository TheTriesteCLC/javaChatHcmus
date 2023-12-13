package GUI;

import Data.ChatRoom;
import Data.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class ClientChatBoxLeft extends JPanel implements ActionListener {
    private JLabel userLabel;
    private JList userList;
    private JButton chatWithUserButton;
    private DefaultListModel<String> userListModel;
    private Client currClient;
    public ClientChatBoxLeft(Client currClient) {
        this.currClient = currClient;
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));


        this.userLabel = new JLabel("Users");
        this.userListModel = new DefaultListModel<String>();
        List<ChatRoom> allChatRooms = this.currClient.getChatRooms();
        for(ChatRoom chatroom : allChatRooms) {
            if(!Objects.equals(chatroom.getType(), "group")){
                this.userListModel.addElement(chatroom.getRoomname());
            }
        }
        this.userList = new JList<>(this.userListModel);
        this.userList.setFont(new Font("Consolas", Font.BOLD, 30));
        JScrollPane usersSroll = new JScrollPane(this.userList);
        this.chatWithUserButton = new JButton("Chat now");

        this.add(userLabel);
        this.add(usersSroll);
        this.add(chatWithUserButton);

        this.chatWithUserButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
