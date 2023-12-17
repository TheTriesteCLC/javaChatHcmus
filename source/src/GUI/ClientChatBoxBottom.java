package GUI;

import Data.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientChatBoxBottom extends JPanel implements ActionListener {
    private Client currClient;
    private JTextField chatField;
    private JButton sendBtn;
    private JButton sendFileBtn;
    private JFileChooser fileChooser;

    public ClientChatBoxBottom(int width, int height, Client currClient) {
        this.currClient = currClient;

        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        this.chatField = new JTextField();
        this.chatField.setFont(new Font("Consolas", Font.BOLD, 25));
        this.chatField.setPreferredSize(new Dimension(300,50));

        JPanel buttonJP = new JPanel();
        buttonJP.setLayout(new BoxLayout(buttonJP,BoxLayout.PAGE_AXIS));
        this.sendBtn = new JButton("Send");
        this.sendFileBtn = new JButton("Send file");
        buttonJP.add(this.sendBtn);
        buttonJP.add(this.sendFileBtn);

        this.add(this.chatField);
        this.add(buttonJP);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
