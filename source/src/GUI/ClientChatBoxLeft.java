package GUI;

import Data.ChatRoom;
import Data.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class ClientChatBoxLeft extends JPanel {
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
        List<String> allChatRooms = this.currClient.getChatRooms();
        for(String chatroom : allChatRooms) {
            this.userListModel.addElement(chatroom);
        }

        this.userList = new JList<>(this.userListModel);
        this.userList.setFont(new Font("Consolas", Font.BOLD, 25));
        JScrollPane usersSroll = new JScrollPane(this.userList);
        usersSroll.setPreferredSize(new Dimension(200,600));
        this.chatWithUserButton = new JButton("Chat now");

        this.add(userLabel);
        this.add(usersSroll);
        this.add(chatWithUserButton);
    }

    public JButton getChatWithUserButton() {
        return this.chatWithUserButton;
    }
    public JList getUserList() {
        return this.userList;
    }
}
