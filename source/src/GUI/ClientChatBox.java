//package GUI;
//
//import Data.ChatRoom;
//import Data.Client;
//import Data.Message;
//import Data.database;
//import Utils.Utils;
//
//import javax.swing.*;
//import javax.swing.border.Border;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class ClientChatBox extends JFrame implements ActionListener {
//    private Client currClient;
//    private JPanel mainJP;
//    private JButton createGroupButton;
//    private ClientChatBoxLeft leftJP;
//    private JPanel centerJP;
//    private JList chatMessageList;
//    private ArrayList<ChatRoom> chatRooms;
//    private DefaultListModel<String> messListModel;
//    private ClientChatBoxBottom bottomJP;
//    private database db;
//    public ClientChatBox(int width, int height, Client currClient, database db)
//    {
//        this.currClient = currClient;
//        this.db = db;
//        this.centerJP = new JPanel();
//        this.messListModel = new DefaultListModel<String>();
//
//        setDefaultLookAndFeelDecorated(true);
//        this.setTitle(currClient.getUsername() + " chatbox");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        this.mainJP = new JPanel();
//        this.mainJP.setLayout(new BorderLayout());
//
//        //Create top panel
//        this.createGroupButton = new JButton("Create Group");
//
//        JPanel topPanel = new JPanel();
//        topPanel.add(this.createGroupButton);
//
//        //Create left panel
//       this.leftJP = new ClientChatBoxLeft(currClient);
//
//       //Create center panel
//        this.leftJP.getUserList().setSelectedIndex(0);
//
//        this.chatRooms = new ArrayList<>();
//        for(String roomName : this.currClient.getChatRooms()) {
//            chatRooms.add(this.db.getAllChatrooms().get(roomName));
//        }
//        for(Message mess : this.db.getAllChatrooms().get(this.chatRooms.get(0).getRoomName()).getMessages()) {
//            String displayMess = String.format("%s : %s",
//                    (Objects.equals(mess.getSender(), this.currClient.getUsername()) ? "You" : mess.getSender()),mess.getContent());
//            this.messListModel.addElement(displayMess);
//        }
//        this.chatMessageList = new JList<>(this.messListModel);
//        this.chatMessageList.setFont(new Font("Consolas", Font.BOLD, 25));
//        JScrollPane messagesScroll = new JScrollPane(this.chatMessageList);
//        messagesScroll.setPreferredSize(new Dimension(620,650));
//
//        this.centerJP.add(messagesScroll);
//        Utils.setBorderWithTitle(this.centerJP,this.chatRooms.get(0).getRoomName());
//
//       //Create bottom panel
//        this.bottomJP = new ClientChatBoxBottom(500,300,this.currClient);
//
//        this.mainJP.add(Utils.setPadding(topPanel,10,10,false,""),BorderLayout.NORTH);
//        this.mainJP.add(Utils.setPadding(this.leftJP,10,10,false,""),BorderLayout.WEST);
//        this.mainJP.add(Utils.setPadding(this.bottomJP,10,10,false,""),BorderLayout.SOUTH);
//        this.mainJP.add(Utils.setPadding(this.centerJP,10,10,false,""),BorderLayout.CENTER);
//
//        this.setContentPane(mainJP);
//        this.setMinimumSize(new Dimension(width, height));
//        this.pack();
//        this.setVisible(true);
//
//        this.leftJP.getChatWithUserButton().addActionListener(this);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == this.leftJP.getChatWithUserButton()) {
//            String chatroomChoose = (String) this.leftJP.getUserList().getSelectedValue();
//            System.out.println(chatroomChoose);
//            this.messListModel.clear();
//            for(Message mess : this.db.getAllChatrooms().get(chatroomChoose).getMessages()) {
//                String displayMess = String.format("%s : %s",
//                        (Objects.equals(mess.getSender(), this.currClient.getUsername()) ? "You" : mess.getSender()),mess.getContent());
//                this.messListModel.addElement(displayMess);
//            }
//            Utils.setBorderWithTitle(this.centerJP,chatroomChoose);
//            this.chatMessageList.updateUI();
//        }
//    }
//}

package GUI;

import Data.ChatRoom;
import Data.Client;
import Data.Message;
import Data.database;
import Utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientChatBox extends JFrame implements ActionListener {
    private Client currClient;
    private JPanel mainJP;
    private JButton createGroupButton;
    private ClientChatBoxLeft leftJP;
    private JPanel centerJP;
    private JList chatMessageList;
    private ArrayList<ChatRoom> chatRooms;
    private DefaultListModel<String> messListModel;
    private ClientChatBoxBottom bottomJP;
    private database db;
    public ClientChatBox(int width, int height, Client currClient, database db)
    {
        this.currClient = currClient;
        this.db = db;
        this.centerJP = new JPanel();
        this.messListModel = new DefaultListModel<String>();

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
        this.leftJP = new ClientChatBoxLeft(currClient);

        //Create center panel
        this.leftJP.getUserList().setSelectedIndex(0);

        this.chatRooms = new ArrayList<>();
        for(String roomName : this.currClient.getChatRooms()) {
            chatRooms.add(this.db.getAllChatrooms().get(roomName));
        }
        for(Message mess : this.db.getAllChatrooms().get(this.chatRooms.get(0).getRoomName()).getMessages()) {
            String displayMess = String.format("%s : %s",
                    (Objects.equals(mess.getSender(), this.currClient.getUsername()) ? "You" : mess.getSender()),mess.getContent());
            this.messListModel.addElement(displayMess);
        }
        this.chatMessageList = new JList<>(this.messListModel);
        this.chatMessageList.setFont(new Font("Consolas", Font.BOLD, 25));
        JScrollPane messagesScroll = new JScrollPane(this.chatMessageList);
        messagesScroll.setPreferredSize(new Dimension(620,650));

        this.centerJP.add(messagesScroll);
        Utils.setBorderWithTitle(this.centerJP,this.chatRooms.get(0).getRoomName());

        //Create bottom panel
        this.bottomJP = new ClientChatBoxBottom(500,300,this.currClient);

        this.mainJP.add(Utils.setPadding(topPanel,10,10,false,""),BorderLayout.NORTH);
        this.mainJP.add(Utils.setPadding(this.leftJP,10,10,false,""),BorderLayout.WEST);
        this.mainJP.add(Utils.setPadding(this.bottomJP,10,10,false,""),BorderLayout.SOUTH);
        this.mainJP.add(Utils.setPadding(this.centerJP,10,10,false,""),BorderLayout.CENTER);

        this.setContentPane(mainJP);
        this.setMinimumSize(new Dimension(width, height));
        this.pack();
        this.setVisible(true);

        this.leftJP.getChatWithUserButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.leftJP.getChatWithUserButton()) {
            String chatroomChoose = (String) this.leftJP.getUserList().getSelectedValue();
            System.out.println(chatroomChoose);
            this.messListModel.clear();
            for(Message mess : this.db.getAllChatrooms().get(chatroomChoose).getMessages()) {
                String displayMess = String.format("%s : %s",
                        (Objects.equals(mess.getSender(), this.currClient.getUsername()) ? "You" : mess.getSender()),mess.getContent());
                this.messListModel.addElement(displayMess);
            }
            Utils.setBorderWithTitle(this.centerJP,chatroomChoose);
            this.chatMessageList.updateUI();
        }
    }
}

