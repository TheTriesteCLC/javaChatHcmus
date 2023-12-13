package Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class database {
    private String[] existedUser;
    private List<ChatRoom> chatRooms;
    private List<List<Message>> messages;
    private final Processing dataProcess;
    public database(Processing dataProcess)
    {
        this.dataProcess = dataProcess;
        this.existedUser = this.dataProcess.getExistedUser();
    }
//    public void addingSearchHistory(String search) {
//        if(search.isEmpty() || search.isBlank()) return;
//        List<String> searchList = new ArrayList<String>(Arrays.asList(this.searchHistory));
//        searchList.add(search);
//        this.searchHistory = searchList.toArray(this.searchHistory);
//    }
//    public void saveHSearchHistory()
//    {
//        this.dataProcess.setSearchHistory(this.searchHistory);
//        this.dataProcess.writeSearchHistory();
//    }
//    public void resetUserDatabase() {
//        this.userDictionary = this.dataProcess.getCloneDictionary();
//    }
    public void addNewUser(String username) {
        List<String> tmp = new ArrayList<>(Arrays.asList(this.existedUser));
        tmp.add(username);
        this.existedUser = tmp.toArray(String[]::new);
    }

    public String[] getExistedUser() {
        return existedUser;
    }
}
