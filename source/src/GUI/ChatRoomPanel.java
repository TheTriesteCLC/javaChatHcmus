//package GUI;
//
//import Data.ChatRoom;
//import Data.Client;
//import Data.Message;
//
//import javax.swing.*;
//import java.awt.*;
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class ChatRoomPanel extends JPanel {
//    private Client currClient;
//    private ArrayList<ChatRoom> rooms;
//    private DefaultListModel<String> messListModel;
//    private JList messList;
//    public ChatRoomPanel(Client currClient, ArrayList<ChatRoom> rooms) {
//        this.currClient = currClient;
//        this.rooms = rooms;
//        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//        this.setBackground(Color.WHITE);
//
//        ArrayList<Message> messages= this.rooms.get(0).getMessages();
//        this.messListModel = new DefaultListModel<String>();
//        for(Message mess : messages) {
//            String displayMess = String.format("%s : %s",
//                    (Objects.equals(mess.getSender(), this.currClient.getUsername()) ? "You" : mess.getSender()),mess.getContent());
//            this.messListModel.addElement(displayMess);
//        }
//
//        this.messList = new JList<>(this.messListModel);
//        messList.setFont(new Font("Consolas", Font.BOLD, 20));
//        JScrollPane messagesScroll = new JScrollPane(this.messList);
//        messagesScroll.setPreferredSize(new Dimension(200,600));
//
//        this.add(messagesScroll);
//    }
//    public void changeRoomchat(String newRoomchat) {
//        for(ChatRoom selectedRoom : this.rooms) {
//            if(Objects.equals(selectedRoom.getRoomName(),newRoomchat)) {
//                this.messListModel = new DefaultListModel<>();
//                for(Message mess : selectedRoom.getMessages()) {
//                    String displayMess = String.format("%s : %s",
//                            (Objects.equals(mess.getSender(), this.currClient.getUsername()) ? "You" : mess.getSender()),mess.getContent());
//                    this.messListModel.addElement(displayMess);
//                }
//                this.messList.updateUI();
//                return;
//            }
//        }
//    }
//}
