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
    public List<Entry> getEntries() {
        if(isLocked){
            throw new IllegalArgumentException("Diary is locked. Unlock to access entries.");
        }
        return new ArrayList<>(entries);
    }

    public Diary(String username, String password){
        validateUsername(username);
        this.username = username;
        this.password = password;
        this.entries = new ArrayList<>();
    }
    public static void validateUsername(String username){
        if(username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("User name cannot be empty");
        }
        if(!username.matches("[A-z0-9_]{3,15}")){
            throw new IllegalArgumentException("Invalid username");
        }
    }
    public Diary(){

    }
    public boolean unlockDiary(String pin) {
    if(pin.equals(password)) {
        this.isLocked = false;
        return true;
    }
    return true;
    }
    public void lockDiary() {
        this.isLocked = true;
    }
    public boolean isLocked() {
        return isLocked;
    }
    public void createEntry(Entry entry) {
        if(!isLocked) {entries.add(entry);
        }else{
            throw new IllegalArgumentException("Diary is locked. Unlock to add entries.");}
    }
    public void deleteEntry(Entry entry) {
        if(!isLocked) {
            entries.remove(entry);

        }else throw new IllegalArgumentException("Diary is locked. Unlock to access entries.");
    }

    public Entry findEntryById(String id) {
        if (isLocked) {
            throw new IllegalArgumentException("Diary is locked. Unlock to access entries.");
        }
        for (Entry entry : entries) {
            if (entry.getId().equals(id)) {return entry;}
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
