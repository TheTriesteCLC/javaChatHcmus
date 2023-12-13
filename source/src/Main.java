import Data.ChatRoom;
import Data.Message;
import Data.Processing;
import Data.database;
import GUI.ChatRoomPanel;
import GUI.ClientChatBox;
import GUI.ClientLoginGUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;

public class Main {
    public static void main(String[] args) {
//        Processing processing = new Processing();
//        database db = new database(processing);
//        ClientLoginGUI loginGui = new ClientLoginGUI(700,600,db);

        //Creating a File object for directory
        String path = "./source/userInfo";

        File file = new File(path);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isDirectory();
            }
        });

        System.out.println(Arrays.toString(directories));
//        for(File x : directories) {
//            System.out.println(x);
//        }


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

//            ClientChatBox newChatBox = new ClientChatBox(500,500,db);
    }
}