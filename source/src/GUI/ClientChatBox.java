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

import Data.*;
import Utils.Utils;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class ClientChatBox extends JFrame implements ActionListener {
    private  int intervalSec;
    private ClientSocket clientSocket;
    private Client currClient;
    private JPanel mainJP;

    //Top panel components
    private JPanel topJP;
    private JLabel newChatroomNameLabel;
    private JTextField newChatroomName;
    private JLabel addingUserLabel;
    private JTextField addingUser;
    private JLabel createChatroomNoti;
    private JButton createChatroomButton;

    //Left panel components
    private JPanel leftJP;
    private JLabel userLabel;
    private JList userList;
    private JButton chatWithUserButton;
    private DefaultListModel<String> userListModel;

    //Center panel components
    private JPanel centerJP;
    private JList chatMessageList;
    private ArrayList<ChatRoom> chatRooms;
    private DefaultListModel<String> messListModel;
    private ChatRoom currChatroom;

    //Bottom panel components
    private ClientChatBoxBottom bottomJP;
    Timer timer;
    public ClientChatBox(int width, int height, ClientSocket clientSocket , Client currClient, int intervalSec)
    {
        this.clientSocket = clientSocket;
        this.currClient = currClient;
        this.centerJP = new JPanel();
        this.messListModel = new DefaultListModel<String>();
        this.intervalSec = intervalSec;

        setDefaultLookAndFeelDecorated(true);
        this.setTitle(currClient.getUsername() + " chatbox");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainJP = new JPanel();
        this.mainJP.setLayout(new BorderLayout());

        //Create top panel
        this.topJP = new JPanel();
        this.topJP.setLayout(new BoxLayout(this.topJP,BoxLayout.LINE_AXIS));
        this.newChatroomNameLabel = new JLabel("New chatroom name (Space and '-' not allowed): ");
        this.newChatroomName = new JTextField();
        this.addingUserLabel = new JLabel("Added users (at least 2 users, seperated by ', '): ");
        this.addingUser = new JTextField();
        this.createChatroomButton = new JButton("Create Group");
        this.createChatroomNoti = new JLabel();

        this.topJP.add(this.newChatroomNameLabel);
        this.topJP.add(this.newChatroomName);
        this.topJP.add(this.addingUserLabel);
        this.topJP.add(this.addingUser);
        this.topJP.add(this.createChatroomButton);
        this.topJP.add(this.createChatroomNoti);

        //Create left panel
        this.leftJP = new JPanel();
        this.leftJP.setLayout(new BoxLayout(this.leftJP,BoxLayout.PAGE_AXIS));

        this.userLabel = new JLabel("Users");
        this.userListModel = new DefaultListModel<String>();
        ArrayList<String> allChatRooms = this.currClient.getChatRooms();
        for(String chatroom : allChatRooms) {
            this.userListModel.addElement(chatroom);
        }

        this.userList = new JList<>(this.userListModel);
        this.userList.setFont(new Font("Consolas", Font.BOLD, 25));
        JScrollPane usersSroll = new JScrollPane(this.userList);
        usersSroll.setPreferredSize(new Dimension(200,600));
        this.chatWithUserButton = new JButton("Chat now");

        this.leftJP.add(userLabel);
        this.leftJP.add(usersSroll);
        this.leftJP.add(chatWithUserButton);

        //Create center panel
        this.userList.setSelectedIndex(0);

        this.chatRooms = new ArrayList<>();
        System.out.println(this.currClient.toString());
        for(String roomName : this.currClient.getChatRooms()) {
            ChatRoom readChatroom = this.clientSocket.getChatroomData(roomName);
            if(readChatroom != null) this.chatRooms.add(readChatroom);
        }
        this.currChatroom = this.chatRooms.get(0);

        for(Message mess : this.chatRooms.get(0).getMessages()) {
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

        this.mainJP.add(Utils.setPadding(this.topJP,10,10,false,""),BorderLayout.NORTH);
        this.mainJP.add(Utils.setPadding(this.leftJP,10,10,false,""),BorderLayout.WEST);
        this.mainJP.add(Utils.setPadding(this.bottomJP,10,10,false,""),BorderLayout.SOUTH);
        this.mainJP.add(Utils.setPadding(this.centerJP,10,10,false,""),BorderLayout.CENTER);

        this.setContentPane(mainJP);
        this.setMinimumSize(new Dimension(width, height));
        this.pack();
        this.setVisible(true);

        this.createChatroomButton.addActionListener(this);
        this.chatWithUserButton.addActionListener(this);
        this.bottomJP.getSendBtn().addActionListener(this);
        this.bottomJP.getSendFileBtn().addActionListener(this);
        this.bottomJP.getDownloadFileBtn().addActionListener(this);
        this.bottomJP.getDeleteMessBtn().addActionListener(this);

        this.timer = new Timer(1000, null);

        this.timer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                refreshCurrentChatpanel();
                refreshChatlist();
            }
        });

        this.timer.setRepeats(true);
        this.timer.setDelay(this.intervalSec * 1000);
        this.timer.start();

    }
    private void refreshChatlist() {
        int selectedIndexChatlist = this.userList.getSelectedIndex();
        Client refreshClient = this.clientSocket.getClientData(currClient.getUsername());
        if(refreshClient != null) {
            ArrayList<String> chatList = refreshClient.getChatRooms();
            this.userListModel.clear();
            for(String chatroom : chatList) {
                this.userListModel.addElement(chatroom);
            }
            this.userList.updateUI();
            this.userList.setSelectedIndex(selectedIndexChatlist);
        }
    }
    private void refreshCurrentChatpanel() {
        String chatroomChoose = this.userList.getSelectedValue().toString();
        ChatRoom readChatroom = this.clientSocket.getChatroomData(chatroomChoose);
        if(readChatroom != null) {
            this.messListModel.clear();
            this.currChatroom = readChatroom;

            for(Message mess : readChatroom.getMessages()) {
                String displayMess = String.format("%s : %s",
                        (Objects.equals(mess.getSender(), this.currClient.getUsername()) ? "You" : mess.getSender()),mess.getContent());
                this.messListModel.addElement(displayMess);
            }
            Utils.setBorderWithTitle(this.centerJP,chatroomChoose);
            this.chatMessageList.updateUI();
        }
    }
    private void sendMessage() {
        String message = this.bottomJP.getChatField().getText();
        if(!message.isBlank()) {
            Message sendMsg = new Message(this.currClient.getUsername(),message,this.currChatroom.getRoomName());
            this.clientSocket.sendMessage(sendMsg);
            String displayMess = String.format("%s : %s",
                    (Objects.equals(sendMsg.getSender(), this.currClient.getUsername()) ? "You" : sendMsg.getSender()),sendMsg.getContent());
            this.messListModel.addElement(displayMess);
            this.chatMessageList.updateUI();

            this.bottomJP.getChatField().setText("");
        }
    }

    private void createNewChatroom() {
        String newChatroomname = this.newChatroomName.getText();
        String addingUsers = this.addingUser.getText();
        if(!this.currClient.getChatRooms().contains(newChatroomname) &&
                !newChatroomname.isBlank() &&
                !addingUsers.isBlank() &&
                !newChatroomname.contains(" ") &&
                addingUsers.split(", ").length >= 2 ) {
            ArrayList<String> addingClients = new ArrayList<>(Arrays.asList(addingUsers.split(", ")));
            addingClients.add(this.currClient.getUsername());

            boolean canCreate = this.clientSocket.createChatroom(newChatroomname,addingClients);
            if(canCreate) {
                this.createChatroomNoti.setForeground(Color.GREEN);
                this.createChatroomNoti.setText("Successfully.");
            }else {
                this.createChatroomNoti.setForeground(Color.RED);
                this.createChatroomNoti.setText("Failed");
            }
        }else {
            this.createChatroomNoti.setForeground(Color.RED);
            this.createChatroomNoti.setText("Failed");
        }
        this.newChatroomName.setText("");
        this.addingUser.setText("");
    }
    private void openFileSendChooser() {
        int x = this.bottomJP.getFileSendChooser().showDialog(this,"Select");
        if(x == JFileChooser.APPROVE_OPTION) {
            File chosenFile = bottomJP.getFileSendChooser().getSelectedFile();
            this.bottomJP.getChatField().setText(chosenFile.getName());
            this.clientSocket.sendFile(chosenFile);
        }
    }
    private void openFileDownChooser() {
        String fileName = this.chatMessageList.getSelectedValue().toString().split(": ")[1];
        if((fileName.contains(".txt") || fileName.contains(".jpg") || fileName.contains(".png"))) {
            int x = this.bottomJP.getFileDownChooser().showOpenDialog(this);
            if(x == JFileChooser.APPROVE_OPTION) {
                String newFileDir = bottomJP.getFileDownChooser().getSelectedFile() + "\\" + fileName;
                System.out.println(newFileDir);
                File newFile = new File(newFileDir);
                this.clientSocket.getSavedFile(newFile);
            }
        }
    }

    private void deleteChosenMessage() {
        String chosenMessage = this.chatMessageList.getSelectedValue().toString() != null ? this.chatMessageList.getSelectedValue().toString() : "";
        if(chosenMessage.contains("You : ")) {
            String chatroomName = this.currChatroom.getRoomName();
            int chosenMessIndex = this.chatMessageList.getSelectedIndex();


            this.clientSocket.deleteMessage(chatroomName,chosenMessIndex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.chatWithUserButton) {
            refreshCurrentChatpanel();

        }
        if(e.getSource() == this.bottomJP.getSendBtn()) {
            sendMessage();
        }
        if(e.getSource() == this.createChatroomButton) {
            createNewChatroom();
        }
        if(e.getSource() == this.bottomJP.getSendFileBtn()) {
            openFileSendChooser();
        }
        if(e.getSource() == this.bottomJP.getDownloadFileBtn()) {
            openFileDownChooser();
        }
        if(e.getSource() == this.bottomJP.getDeleteMessBtn()) {
            deleteChosenMessage();
        }
    }
}

