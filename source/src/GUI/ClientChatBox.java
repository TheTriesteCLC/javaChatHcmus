package GUI;

import Data.database;
import Utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientChatBox extends JFrame implements ActionListener {
    private JPanel mainJP;
    private JButton chatUsersButton;
    private JButton chatGroupButton;
    private JButton createGroupButton;
    private JList<String> onlineUserJList;
    private JList<String> groupJList;

    private database db;

    public ClientChatBox(int width, int height, database db)
    {
        this.db = db;

        setDefaultLookAndFeelDecorated(true);
        this.setTitle("Login/Sign-up");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainJP = new JPanel();
        this.mainJP.setLayout(new BorderLayout());

        //Create top panel
        this.chatUsersButton = new JButton("Other users");
        this.chatGroupButton = new JButton("Groups");

        JPanel topPanel = new JPanel();
        topPanel.add(this.chatUsersButton);
        topPanel.add(this.chatGroupButton);

        //Create right panel


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
