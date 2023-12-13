package GUI;

import Data.ChatRoom;
import Data.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class ClientChatBoxRight extends JPanel implements ActionListener {
    private JLabel groupLabel;
    private JList groupList;
    private JButton chatWithGroupButton;
    private DefaultListModel<String> groupListModel;
    private Client currClient;
    public ClientChatBoxRight(Client currClient) {
        this.currClient = currClient;
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));


        this.groupLabel = new JLabel("Groups");
        this.groupListModel = new DefaultListModel<String>();
        List<ChatRoom> allChatRooms = this.currClient.getChatRooms();
        for(ChatRoom chatroom : allChatRooms) {
            if(Objects.equals(chatroom.getType(), "group")){
                this.groupListModel.addElement(chatroom.getRoomname());
            }
        }
        this.groupList = new JList<>(this.groupListModel);
        this.groupList.setFont(new Font("Consolas", Font.BOLD, 30));
        JScrollPane groupsSroll = new JScrollPane(this.groupList);
        this.chatWithGroupButton = new JButton("Chat now");

        this.add(groupLabel);
        this.add(groupsSroll);
        this.add(chatWithGroupButton);

        this.chatWithGroupButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
