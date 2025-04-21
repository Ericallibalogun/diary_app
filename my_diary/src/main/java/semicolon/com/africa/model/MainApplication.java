package main.java.semicolon.com.africa.model;
import javax.swing.JOptionPane;
import java.awt.*;
import java.util.Scanner;

public class MainApplication {
    static Diaries diaries = new Diaries();
    static Diary diary = new Diary();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String menu = JOptionPane.showInputDialog("""
                    Welcome to Eric Diary App
                    1  =>> Create a new diary
                    2  =>> Add an entry.
                    3  =>> Find Entry by ID
                    4  =>> View entries
                    5  =>> Delete Entry
                    6  =>> Update Entry
                    7  =>> Lock Diary
                    8  =>> Unlock Diary
                    9  =>> Find Diary
                    11 =>> Delete Diary
                    10 =>> Exit
                    """);

            switch (menu){
                case "1":
                    createDiary();
                    break;
                case "2":
                    addEntryToADiary();
                    break;
                case "3":
                    findEntryById();
                    break;
            }
        }
    }
    public static void createDiary(){
        String name = JOptionPane.showInputDialog("Enter your username: ");
        String password = JOptionPane.showInputDialog("create ur password: ");
        Diary myDiary = new Diary(name,password);
        diaries.addDiary(myDiary);
        JOptionPane.showMessageDialog(null,"Diary created successfully");
    }
    public static void addEntryToADiary() {
        String name = JOptionPane.showInputDialog("Enter your username: ");
        String password = JOptionPane.showInputDialog("create ur password: ");
        Diary diary = diaries.findByUserName(name);

        if (diary == null) {
            JOptionPane.showMessageDialog(null, "Diary not found for the given username.");
        }
        if (diary != null && !diary.unlockDiary(password)) {
            JOptionPane.showMessageDialog(null, "Invalid password");
        }
        String id = JOptionPane.showInputDialog("Enter your ID");
        String title = JOptionPane.showInputDialog("Enter your title");
        String body = JOptionPane.showInputDialog("What's the gist for today");

        try {
            Entry myEntry = new Entry(id, title, body);
            if (diary != null) {
                diary.createEntry(myEntry);
            }
            JOptionPane.showMessageDialog(null, "Entry created successfully");
        }catch (IllegalArgumentException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
    }
    public static void findEntryById() {
        if(diary == null){
            JOptionPane.showMessageDialog(null,"No diary selected. Please create or select a diary.");
        }
        String id = JOptionPane.showInputDialog("Enter your ID to check");
        try{
            Entry entry = diary.findEntryById(id);
            if(entry != null){
                JOptionPane.showMessageDialog(null,"Entry found: " + entry.toString());
            }else JOptionPane.showMessageDialog(null,"Entry not found.");
        }catch(IllegalArgumentException error){
            JOptionPane.showMessageDialog(null,error.getMessage());}
    }
}


