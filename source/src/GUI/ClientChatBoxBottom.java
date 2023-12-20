package GUI;

import Data.Client;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientChatBoxBottom extends JPanel{
    private Client currClient;
    private JTextField chatField;
    private JButton sendBtn;
    private JButton sendFileBtn;
    private JButton downloadFileBtn;
    private JButton deleteMessBtn;
    private JFileChooser fileSendChooser;
    private JFileChooser fileDownChooser;

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
        this.downloadFileBtn = new JButton("Download File");
        this.deleteMessBtn = new JButton("Delete my message");
        buttonJP.add(this.sendBtn);
        buttonJP.add(this.sendFileBtn);
        buttonJP.add(this.downloadFileBtn);
        buttonJP.add(this.deleteMessBtn);

        //Setting FileSendChooser
        this.fileSendChooser = new JFileChooser();
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Hinh anh, txt", "ipg", "png", "txt");
        fileSendChooser.setFileFilter(fileFilter);
        fileSendChooser.setMultiSelectionEnabled(false);

        //Setting FileDownChooser
        this.fileDownChooser = new JFileChooser();
        this.fileDownChooser.setCurrentDirectory(new java.io.File("."));
        this.fileDownChooser.setDialogTitle("Save");
        this.fileDownChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.fileDownChooser.setAcceptAllFileFilterUsed(false);

        this.add(this.chatField);
        this.add(buttonJP);
    }
    public JTextField getChatField() {
        return this.chatField;
    }
    public JButton getSendBtn() {
        return this.sendBtn;
    }
    public JButton getSendFileBtn() {
        return this.sendFileBtn;
    }
    public JButton getDownloadFileBtn() {return this.downloadFileBtn;}
    public JButton getDeleteMessBtn() {return this.deleteMessBtn;}
    public JFileChooser getFileSendChooser() { return this.fileSendChooser;}
    public JFileChooser getFileDownChooser() { return this.fileDownChooser;}
}
