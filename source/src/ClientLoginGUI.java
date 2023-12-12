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

import static Utils.Utils.setPadding;

public class ClientLoginGUI extends JFrame implements ActionListener {
    private JPanel mainJP;
    private JLabel userNameLoginLabel;
    private JTextField userNameLogin;
    private JButton loginButton;
    private JLabel loginNoti;
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



        this.userNameLoginLabel = new JLabel("Username:");
        this.userNameLogin = new JTextField();
        this.loginButton = new JButton("Log in");
        this.loginNoti = new JLabel("Enter new username");
        userNameLogin.setPreferredSize(new Dimension(200,25));
        userNameLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel loginPanel = new JPanel();
        loginPanel.add(this.userNameLoginLabel);
        loginPanel.add(this.userNameLogin);
        loginPanel.add(this.loginButton);
        loginPanel.add(this.loginNoti);


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


        this.mainJP.add(Utils.setPadding(loginPanel,25, 25,false));
        this.mainJP.add(Utils.setPadding(signUpPanel,25,25,false));
        this.mainJP.add(Utils.setPadding(usernameList,25,25,false));

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
            if(Arrays.asList(this.db.getExistedUser()).contains(loginUsername)) {
                this.loginNoti.setForeground(Color.GREEN);
                this.loginNoti.setText("Login successfully.");
            }else {
                this.loginNoti.setForeground(Color.RED);
                this.loginNoti.setText("Username not found.");
            }
        }
        if(e.getSource() == this.signUpButton)
        {
            String newUsername = this.userNameSignUp.getText();
            if(!Arrays.asList(this.db.getExistedUser()).contains(newUsername)) {
                this.signupNoti.setForeground(Color.GREEN);
                this.signupNoti.setText("Create new user successfully.");
                this.db.addNewUser(newUsername);

                this.usernameListModel.addElement(newUsername);
                this.userName.updateUI();
            }else {
                this.signupNoti.setForeground(Color.RED);
                this.signupNoti.setText("Username existed.");
            }
        }
    }
}
