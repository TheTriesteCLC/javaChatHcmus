import Data.Processing;
import Data.database;

public class Main {
    public static void main(String[] args) {
        Processing processing = new Processing();
        database db = new database(processing);
        ClientLoginGUI loginGui = new ClientLoginGUI(800,500,db);
    }
}