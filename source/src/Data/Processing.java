//package Data;
//
//import java.io.*;
//
//public class Processing {
//    private String userFileName;
//    private int userFileLength;
//    public Processing() {
//        this.existedUserFileName = "source/userName.txt";
//        this.existedUserFileLength = getFileLength(this.existedUserFileName);
//
//        loadingUsername();
//    }
//    public Processing(String existedUserFileLength) {
//        this.existedUserFileName = existedUserFileLength;
//        this.existedUserFileLength = getFileLength(this.existedUserFileName);
//
//        loadingUsername();
//    }
//    public static int getFileLength(String fileName)
//    {
//        int lines = 0;
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//            while (reader.readLine() != null) lines++;
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return lines;
//    }
//    private void loadingUsername()
//    {
//        try(BufferedReader reader = new BufferedReader(new FileReader(this.existedUserFileName))) {
//            reader.readLine();
//            int i = 0;
//            this.existedUser = new String[this.existedUserFileLength - 1];
//
//            while(i < this.existedUserFileLength - 1)
//            {
//                String user = reader.readLine();
//
//                this.existedUser[i] = user;
//                i++;
//            }
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
////    public void writeExistedUser()
////    {
////        try(BufferedWriter writer = new BufferedWriter(
////                new OutputStreamWriter(new FileOutputStream(this.historyFileName, false)))) {
////            int i = 0;
////            writer.write("History search");
////            while(i < this.searchHistory.length)
////            {
////                writer.write("\n");
////                writer.write(this.searchHistory[i]);
////                i++;
////            }
////            writer.close();
////        } catch (FileNotFoundException e) {
////            throw new RuntimeException(e);
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////    }
////    public void setSearchHistory(String[] searchHistory)
////    {
////        this.searchHistory = searchHistory;
////    }
////    public HashMap<String, String[]> getCloneDictionary() {
////        return (HashMap<String, String[]>) this.slangDictionary.clone();
////    }
////    public String[] getHistory() {
////        return this.searchHistory;
////    }
//
//    public String[] getExistedUser() {
//        return existedUser;
//    }
//}
