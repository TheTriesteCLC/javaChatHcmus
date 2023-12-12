package GUI;

import Data.Processing;
import Data.database;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import static Utils.Utils.setPadding;

public class ClientLoginGUI extends JFrame implements ActionListener {
    private JPanel mainJP;
    private JLabel userNameLoginLabel;
    private JTextField userNameLogin;
    private JButton loginButton;
    private JLabel loginNoti;
    private JLabel serverPortLabel;
    private JTextField serverPort;
    private JLabel serverIPLabel;
    private JTextField serverIP;
    private JLabel serverNoti;
    private JLabel userNameSignUpLabel;
    private JTextField userNameSignUp;
    private JButton signUpButton;
    private JLabel signupNoti;
    private JLabel userNameListLabel;
    private JList userName;
    private DefaultListModel<String> usernameListModel;
    private database db;
    public ClientLoginGUI(int width, int height, database db)
    {
        this.db = db;

        setDefaultLookAndFeelDecorated(true);
        this.setTitle("Login/Sign-up");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainJP = new JPanel();
        this.mainJP.setLayout(new BoxLayout(this.mainJP, BoxLayout.PAGE_AXIS));

        //Create Login panel
        this.userNameLoginLabel = new JLabel("Username:");
        this.userNameLogin = new JTextField();
        this.loginButton = new JButton("Log in and connect to server");
        this.loginNoti = new JLabel();
        userNameLogin.setPreferredSize(new Dimension(200,25));
        userNameLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel loginPanel = new JPanel();
        loginPanel.add(this.userNameLoginLabel);
        loginPanel.add(this.userNameLogin);
        loginPanel.add(this.loginButton);
        loginPanel.add(this.loginNoti);

        //Create IP and port panel
        this.serverIPLabel = new JLabel("Server IP:");
        this.serverIP = new JTextField();
        this.serverPortLabel = new JLabel("Server Port:");
        this.serverPort = new JTextField();
        this.serverNoti = new JLabel();
        serverIP.setPreferredSize(new Dimension(100,25));
        serverIP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        serverPort.setPreferredSize(new Dimension(100,25));
        serverPort.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel serverInfoPanel = new JPanel();
        serverInfoPanel.add(this.serverIPLabel);
        serverInfoPanel.add(this.serverIP);
        serverInfoPanel.add(this.serverPortLabel);
        serverInfoPanel.add(this.serverPort);
        serverInfoPanel.add(this.serverNoti);


        //Create Signup panel
        this.userNameSignUpLabel = new JLabel("Username:");
        this.userNameSignUp = new JTextField();
        this.signUpButton = new JButton("Sign up");
        this.signupNoti = new JLabel();
        userNameSignUp.setPreferredSize(new Dimension(200,25));
        userNameSignUp.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        JPanel signUpPanel = new JPanel();
        signUpPanel.add(this.userNameSignUpLabel);
        signUpPanel.add(this.userNameSignUp);
        signUpPanel.add(this.signUpButton);
        signUpPanel.add(this.signupNoti);

        //Create Existed Username List panel
        this.userNameListLabel = new JLabel("Existed username");

        this.usernameListModel = new DefaultListModel<String>();
        for(String user : this.db.getExistedUser()) {
            this.usernameListModel.addElement(user);
        }

        this.userName = new JList(this.usernameListModel);
        userName.setFont(new Font("Consolas", Font.BOLD, 20));
        JScrollPane userNameScroll = new JScrollPane(this.userName);
        userNameScroll.setMaximumSize(new Dimension(200, 200));
        userNameScroll.setMinimumSize (new Dimension (200,200));

        JPanel usernameList = new JPanel();
        usernameList.add(userNameListLabel);
        usernameList.add(userNameScroll);

        //Adding panels to frame
        this.mainJP.add(Utils.setPadding(serverInfoPanel,0,0,false));
        this.mainJP.add(Utils.setPadding(loginPanel,0, 0,false));
        this.mainJP.add(Utils.setPadding(signUpPanel,0,0,false));
        this.mainJP.add(Utils.setPadding(usernameList,0,0,false));

        this.setContentPane(mainJP);
        this.setMinimumSize(new Dimension(width, height));
        this.pack();
        this.setVisible(true);

        this.loginButton.addActionListener(this);
        this.signUpButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.loginButton)
        {
            String loginUsername = this.userNameLogin.getText();
            String IPServer = this.serverIP.getText();
            String PortServer = this.serverPort.getText();
            if(Arrays.asList(this.db.getExistedUser()).contains(loginUsername) &&
                    Objects.equals(IPServer, "127.0.0.1") && Objects.equals(PortServer, "80")) {
                this.serverNoti.setForeground(Color.GREEN);
                this.serverNoti.setText("Connect successfully.");
                this.loginNoti.setForeground(Color.GREEN);
                this.loginNoti.setText("Login successfully.");
            }else if(!Objects.equals(IPServer, "127.0.0.1") && !Objects.equals(PortServer, "80")){
                this.serverNoti.setForeground(Color.RED);
                this.serverNoti.setText("Server not found.");
            }else {
                this.loginNoti.setForeground(Color.RED);
                this.loginNoti.setText("Username not found.");
            }
        }
        if(e.getSource() == this.signUpButton)
        {
            String newUsername = this.userNameSignUp.getText();
            if(!Arrays.asList(this.db.getExistedUser()).contains(newUsername) &&
            !newUsername.trim().isEmpty()) {
                this.signupNoti.setForeground(Color.GREEN);
                this.signupNoti.setText("Create new user successfully.");
                this.db.addNewUser(newUsername);

                this.usernameListModel.addElement(newUsername);
                this.userName.updateUI();
            }else if(newUsername.trim().isEmpty()){
                this.signupNoti.setForeground(Color.RED);
                this.signupNoti.setText("Username cannot be empty.");
            }else {
                this.signupNoti.setForeground(Color.RED);
                this.signupNoti.setText("Username existed.");
            }
        }
    }
}
