//package GUI;
//
//import Data.ChatRoom;
//
//import javax.swing.*;
//
//public class ChatBoxUpdate extends Thread{
//    private JList updateList;
//    private int intervalSec;
//    private DefaultListModel<String> listModel;
//    public ChatBoxUpdate(JList updateList, DefaultListModel<String> listModel , int intervalSec) {
//        this.updateList = updateList;
//        this.listModel = listModel;
//        this.intervalSec = intervalSec;
//    }
//    @Override
//    public void run() {
//        while (true) {
//
//            try {
//                Thread.sleep(this.intervalSec);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        }
//    }
//}
