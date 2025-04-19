package main.java.semicolon.com.africa.model;
import java.util.ArrayList;
import java.util.List;

public class Diary {
    private String username;
    private String password;
    private boolean isLocked = true;
    private List<Entry> entries;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public List<Entry> getEntries() {
        return entries;
    }

    public Diary(String username, String password){
        this.username = username;
        this.password = password;
        this.entries = new ArrayList<>();
    }
    public Diary(){

    }
    public void unlockDiary(String pin) {
    if(pin.equals(password)) {
        isLocked = false;
    }

    }
    public boolean lockDiary() {
        isLocked();
        return isLocked;
    }
    public boolean isLocked() {
        if(isLocked == true) {
            return true;
        }else {
            return false;
        }

    }
    public void createEntry(Entry entry) {
        entries.add(entry);
    }
    public void deleteEntry(Entry entry) {
        entries.remove(entry);
    }
    public Entry findEntryById(String id) {
        for(Entry entry : entries) {
            if(entry.getId().equalsIgnoreCase(id)) {
                return entry;
            }
        }
        return null;
    }
    public void updateEntry(String id,String title, String body) {
        for(Entry entry : entries) {
        if(entry.getId().equalsIgnoreCase(id)){
            entry.setTitle(title);
            entry.setBody(body);
        }
        }
    }


    @Override
    public String toString() {
        return "Diary{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isLocked=" + isLocked +
                ", entries=" + entries +
                '}';
    }
}
