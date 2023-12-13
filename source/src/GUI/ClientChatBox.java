package GUI;

import Data.ChatRoom;
import Data.Client;
import Data.database;
import Utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class ClientChatBox extends JFrame implements ActionListener {
    private Client currClient;
    private JPanel mainJP;
    private JButton createGroupButton;
    private JLabel userLabel;
    private JList userList;
    private JButton chatWithUser;
    private DefaultListModel<String> userListModel;
    private JLabel groupLabel;
    private JList groupList;
    private JButton chatWithGroup;
    private DefaultListModel<String> groupListModel;


    public ClientChatBox(int width, int height, Client currClient)
    {
        this.currClient = currClient;

        setDefaultLookAndFeelDecorated(true);
        this.setTitle(currClient.getUsername() + " chatbox");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainJP = new JPanel();
        this.mainJP.setLayout(new BorderLayout());

        //Create top panel
        this.createGroupButton = new JButton("Create Group");

        JPanel topPanel = new JPanel();
        topPanel.add(this.createGroupButton);

        //Create left panel
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






        this.mainJP.add(Utils.setPadding(topPanel,10,10,false),BorderLayout.NORTH);

        this.setContentPane(mainJP);
        this.setMinimumSize(new Dimension(width, height));
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
