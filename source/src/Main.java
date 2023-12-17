import Data.ChatRoom;
import Data.Client;
import Data.database;
import GUI.ClientChatBox;
import GUI.ClientLoginGUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;

public class Main {
    public static void main(String[] args) {
        database db = new database();
        ClientLoginGUI loginGui = new ClientLoginGUI(700,600);


//        List<String> clients = List.of(new String[]{"Anh", "Thu", "Tu"});
//
//        ChatRoom room = new ChatRoom("room 1","group", clients);
//        for(int i = 0; i < 3; ++i) {
//            room.addMessage(new Message("Tu","Hello mn","room1"));
//        }
//        for(int i = 0; i < 100; ++i) {
//            room.addMessage(new Message("Anh","Gi z ba","room1"));
//        }
//        ChatRoomPanel chatRoomPanel = new ChatRoomPanel("Anh",room);
//
//
//        JFrame mainFrame = new JFrame();
//        setDefaultLookAndFeelDecorated(true);
//        mainFrame.setTitle("Login/Sign-up");
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        mainFrame.setContentPane(chatRoomPanel);
//        mainFrame.setMinimumSize(new Dimension(500, 500));
//        mainFrame.pack();
//        mainFrame.setVisible(true);

//        Client client = db.getAllClients().get("tom");
//        ChatRoom chatroom = db.getAllChatrooms().get("haha");
////        ClientChatBox newChatBox = new ClientChatBox(500,500,client);
//        ClientChatBox clientChatBox = new ClientChatBox(900,900,client, db);
//        JFrame test = new JFrame();
//        test.setDefaultLookAndFeelDecorated(true);
//        test.setTitle("thu chatbox");
//        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        ChatRoomPanel newChatRoomPanel = new ChatRoomPanel(client,chatroom);
//
//        test.setContentPane(newChatRoomPanel);
//        test.setMinimumSize(new Dimension(500, 500));
//        test.pack();
//        test.setVisible(true);
//        ClientLoginGUI clientLoginGUI = new ClientLoginGUI(500,500, db);
    }
}