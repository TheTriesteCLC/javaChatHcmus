import Data.Processing;
import Data.database;
import GUI.ClientChatBox;
import GUI.ClientLoginGUI;

public class Main {
    public static void main(String[] args) {
        Processing processing = new Processing();
        database db = new database(processing);
        ClientLoginGUI loginGui = new ClientLoginGUI(700,600,db);

    }
}