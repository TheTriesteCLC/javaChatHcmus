package Data;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;

public class ClientSocket  {
    public Socket clientSocket;
    public String socketIP;
    public int socketPort;

    public ObjectOutputStream oos;
    public BufferedWriter bw;

    public ObjectInputStream ois;
    public BufferedReader br;
    public DataInputStream dis;
    public DataOutputStream dos;
    public static String createNewClient = "POST newClient";
    public static final String getUsernameReq = "GET userNameList all";
    public static final String getClientData = "GET clientData all";
    public static final String getChatRoomData = "GET chatroomData all";
    public static final String sendMessage = "POST Message";
    public static final String createNewChatroom = "POST newChatroom";
    public static final String sendNewFile = "POST newFile";
    public static final String getFile = "GET fileData";
    public static final String deleteMessage = "DELETE Message";

    public ClientSocket() {
    }

    public ClientSocket(String socketIP, int socketPort) {
        this.socketIP = socketIP;
        this.socketPort = socketPort;
        try {
            this.clientSocket = new Socket(socketIP, socketPort);
            System.out.println("Connecting to " + socketIP + " on port " + socketPort);

            OutputStream os = clientSocket.getOutputStream();
            this.oos = new ObjectOutputStream(os);
            this.bw = new BufferedWriter(new OutputStreamWriter(os));
            this.dos = new DataOutputStream(os);

            InputStream is = clientSocket.getInputStream();
            this.ois = new ObjectInputStream(is);
            this.br = new BufferedReader(new InputStreamReader(is));
            this.dis = new DataInputStream(is);
        } catch (UnknownHostException e) {
            System.out.println("UnknownHost exception !!!!");
        } catch (IOException e) {
            System.out.println("IO exception ClientSocket!!!!");
        }
    }
    public ArrayList<String> getUsername() {
        try {
            this.bw.write(getUsernameReq);
            this.bw.newLine();
            this.bw.flush();

            return (ArrayList<String>) ois.readObject();
        } catch (IOException ex) {
            System.out.println("IO exception getUsername!!!!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found getUsername!!!!");
        }
        return null;
    }
    public void createNewUser(Client newClient) {
        try {
            this.bw.write(createNewClient);
            this.bw.newLine();
            this.bw.flush();

            this.oos.writeUnshared(newClient);
            this.oos.reset();
        } catch (IOException e) {
            System.out.println("IO exception createNewUser!!!!");
        }
    }
    public Client getClientData(String currClient) {
        try {
            String getCurrClientData = getClientData.replace("all", currClient);
            this.bw.write(getCurrClientData);
            this.bw.newLine();
            this.bw.flush();

            return (Client) ois.readUnshared();
        } catch (IOException ex) {
            System.out.println("IO exception getClientData!!!!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found getClientData!!!!");
        }
        return null;
    }
    public ChatRoom getChatroomData(String chatroom) {
        try {
            String getChatroomData = getChatRoomData.replace("all", chatroom);
            this.bw.write(getChatroomData);
            this.bw.newLine();
            this.bw.flush();

            return (ChatRoom) ois.readUnshared();
        } catch (IOException ex) {
            System.out.println("IO exception getChatroomData!!!!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found getChatroomData!!!!");
        }
        return null;
    }
    public void sendMessage(Message msg) {
        try {
            this.bw.write(sendMessage);
            this.bw.newLine();
            this.bw.flush();

            this.oos.writeUnshared(msg);
            this.oos.reset();
        } catch (IOException ex) {
            System.out.println("IO exception sendMessage!!!!");
        }
    }
    public boolean createChatroom(String newChatroom, ArrayList<String> addingClients) {
        try {
            this.bw.write(createNewChatroom + " " + newChatroom);
            this.bw.newLine();
            this.bw.flush();

            this.oos.writeUnshared(addingClients);
            this.oos.reset();

            String reply = this.br.readLine();
            System.out.println(reply);
            String expectedReply = "Create " + newChatroom;
            return reply.contains(expectedReply);
        } catch (IOException e) {
            System.out.println("IO exception createChatroom!!!!");
        }
        return false;
    }
    public void sendFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            this.bw.write(sendNewFile +  " " + file.getName() + " " + file.length());
            this.bw.newLine();
            this.bw.flush();

            int bytes = 0;
            byte[] buffer = new byte[4 * 1024];
            while ((bytes = fileInputStream.read(buffer)) != -1) {

                dos.write(buffer, 0, bytes);
                dos.flush();
            }
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("IO exception sendFile!!!!");
        }
    }
    public void getSavedFile(File file) {
        try {
            this.bw.write(getFile +  " " + file.getName());
            this.bw.newLine();
            this.bw.flush();

            String reply = this.br.readLine();
            if(!reply.contains("Not found")) {
                long fileLength = Long.parseLong(reply.split(" ")[1]);

                int bytes = 0;
                FileOutputStream fileOutputStream
                        = new FileOutputStream(file);


                byte[] buffer = new byte[4 * 1024];
                while (fileLength > 0
                        && (bytes = this.dis.read(buffer, 0, (int)Math.min(buffer.length, fileLength))) != -1) {

                    fileOutputStream.write(buffer, 0, bytes);
                    fileLength -= bytes;
                }

                System.out.println(file + " is saved.");
                fileOutputStream.close();
            }

        } catch (IOException ex) {
            System.out.println("IO exception getSavedFile!!!!");
        }
    }
    public void deleteMessage(String chatroom, int messageIndex) {
        try {
            this.bw.write(deleteMessage +  " " + chatroom + " " + messageIndex);
            this.bw.newLine();
            this.bw.flush();
        } catch (IOException ex) {
            System.out.println("IO exception deleteMessage!!!!");
        }
    }
}
