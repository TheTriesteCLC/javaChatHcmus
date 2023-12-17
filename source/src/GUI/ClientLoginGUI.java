package GUI;

import Data.ChatRoom;
import Data.Client;
import Data.database;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.*;

import static Utils.Utils.setPadding;

public class ClientLoginGUI extends JFrame implements ActionListener {
    private JPanel mainJP;
    private JPanel serverPanel;
    private JLabel serverPortLabel;
    private JTextField serverPort;
    private JLabel serverIPLabel;
    private JTextField serverIP;
    private JLabel serverNoti;
    private JButton serverConnectButton;
    private JPanel loginPanel;
    private JLabel userNameLoginLabel;
    private JTextField userNameLogin;
    private JButton loginButton;
    private JLabel loginNoti;
    private JLabel userNameSignUpLabel;
    private JTextField userNameSignUp;
    private JPanel signupPanel;
    private JButton signUpButton;
    private JLabel signupNoti;
    private JLabel userNameListLabel;
    private JList userName;
    private ArrayList<String> usernameList;
    private DefaultListModel<String> usernameListModel;
    public ClientLoginGUI(int width, int height)
    {
        setDefaultLookAndFeelDecorated(true);
        this.setTitle("Login/Sign-up");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainJP = new JPanel();
        this.mainJP.setLayout(new BoxLayout(this.mainJP, BoxLayout.PAGE_AXIS));

        //Create Server panel
        this.serverIPLabel = new JLabel("Server IP:");
        this.serverIP = new JTextField();
        this.serverPortLabel = new JLabel("Server Port:");
        this.serverPort = new JTextField();
        this.serverConnectButton = new JButton("Connect to server");
        this.serverNoti = new JLabel();
        serverIP.setMaximumSize(new Dimension(100,25));
        serverIP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        serverPort.setMaximumSize(new Dimension(100,25));
        serverPort.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.serverPanel = new JPanel();
        this.serverPanel.setLayout(new BoxLayout(this.serverPanel, BoxLayout.LINE_AXIS));
        this.serverPanel.add(this.serverIPLabel);
        this.serverPanel.add(this.serverIP);
        this.serverPanel.add(this.serverPortLabel);
        this.serverPanel.add(this.serverPort);
        this.serverPanel.add(this.serverConnectButton);
        this.serverPanel.add(this.serverNoti);


        //Create Login panel
        this.userNameLoginLabel = new JLabel("Username:");
        this.userNameLogin = new JTextField();
        this.loginButton = new JButton("Log in");
        this.loginNoti = new JLabel();
        userNameLogin.setMaximumSize(new Dimension(200,25));
        userNameLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.loginPanel = new JPanel();
        this.loginPanel.setLayout(new BoxLayout(this.loginPanel, BoxLayout.LINE_AXIS));
        loginPanel.add(this.userNameLoginLabel);
        loginPanel.add(this.userNameLogin);
        loginPanel.add(this.loginButton);
        loginPanel.add(this.loginNoti);

        //Create Signup panel
        this.userNameSignUpLabel = new JLabel("Username:");
        this.userNameSignUp = new JTextField();
        this.signUpButton = new JButton("Sign up");
        this.signupNoti = new JLabel();
        userNameSignUp.setPreferredSize(new Dimension(200,25));
        userNameSignUp.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        this.signupPanel = new JPanel();
        signupPanel.add(this.userNameSignUpLabel);
        signupPanel.add(this.userNameSignUp);
        signupPanel.add(this.signUpButton);
        signupPanel.add(this.signupNoti);

        //Create Existed Username List panel
        this.userNameListLabel = new JLabel("Existed username");

        this.usernameList = new ArrayList<String>();
        this.usernameListModel = new DefaultListModel<String>();

        this.userName = new JList(this.usernameListModel);
        userName.setFont(new Font("Consolas", Font.BOLD, 18));
        JScrollPane userNameScroll = new JScrollPane(this.userName);
        userNameScroll.setPreferredSize(new Dimension(300,300));

        JPanel usernameList = new JPanel();
        usernameList.add(userNameListLabel);
        usernameList.add(userNameScroll);

        //Adding panels to frame
        this.mainJP.add(Utils.setPadding(this.serverPanel,20,20,false,""));
        this.mainJP.add(Utils.setPadding(this.loginPanel,20, 20,false,""));
        this.mainJP.add(Utils.setPadding(this.signupPanel,20,20,false,""));
        this.mainJP.add(Utils.setPadding(usernameList,20,20,false,""));

        this.setContentPane(mainJP);
        this.setMinimumSize(new Dimension(width, height));
        this.pack();
        this.setVisible(true);

        this.serverConnectButton.addActionListener(this);
        this.loginButton.addActionListener(this);
        this.signUpButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.serverConnectButton) {
            String IP = this.serverIP.getText();
            String port = this.serverPort.getText();

            Socket socketUserList = new Socket();
            try {
                socketUserList = new Socket(IP, Integer.parseInt(port));
                System.out.println("Connecting to " + IP + " on port " + port);

                InputStream is = socketUserList.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);

                this.usernameList = (ArrayList<String>) ois.readObject();
            } catch (IOException ex) {
                System.out.println("Runtime exception !!!!");
            } catch (ClassNotFoundException ex) {
                System.out.println("Class not found !!!!");
            }
            if(socketUserList.isConnected()) {
                for(String username : this.usernameList) {
                    usernameListModel.addElement(username);
                }
                this.userName.updateUI();
                this.serverNoti.setForeground(Color.GREEN);
                this.serverNoti.setText("Connect successfully.");
            }else {
                this.serverNoti.setForeground(Color.RED);
                this.serverNoti.setText("Server not found.");
            }
        }
//        if(e.getSource() == this.loginButton)
//        {
//            String loginUsername = this.userNameLogin.getText();
//            String IPServer = this.serverIP.getText();
//            String PortServer = this.serverPort.getText();
//
//            if(this.usernameList.isEmpty()) {
//                this.loginNoti.setForeground(Color.GREEN);
//                this.loginNoti.setText("Cannot login, please conenct to server first");
//            }else if(this.usernameList.contains(loginUsername)) {
//                this.loginNoti.setForeground(Color.GREEN);
//                this.loginNoti.setText("Login successfully.");
//
//                ClientChatBox chatbox = new ClientChatBox(700,600);
//            }
//
//            if(Arrays.asList(allClients.keySet()).contains(loginUsername)) {
//
//
////
//            }else if(!Objects.equals(IPServer, "127.0.0.1") && !Objects.equals(PortServer, "80")){
//                this.serverNoti.setForeground(Color.RED);
//                this.serverNoti.setText("Server not found.");
//            }else {
//                this.loginNoti.setForeground(Color.RED);
//                this.loginNoti.setText("Username not found.");
//            }
//        }
//        if(e.getSource() == this.signUpButton)
//        {
//            String newUsername = this.userNameSignUp.getText();
//            Client newClient = new Client(newUsername ,new ArrayList<String>());
//            HashMap<String,Client> allClients = this.db.getAllClients();;
//            if(!Arrays.asList(allClients.keySet()).contains(newUsername) &&
//            !newUsername.trim().isEmpty()) {
//                this.signupNoti.setForeground(Color.GREEN);
//                this.signupNoti.setText("Create new user successfully.");
//                this.db.addNewUser(newClient);
//
//                this.usernameListModel.addElement(newUsername);
//                this.userName.updateUI();
//            }else if(newUsername.trim().isEmpty()){
//                this.signupNoti.setForeground(Color.RED);
//                this.signupNoti.setText("Username cannot be empty.");
//            }else {
//                this.signupNoti.setForeground(Color.RED);
//                this.signupNoti.setText("Username existed.");
//            }
//        }
    }
}
