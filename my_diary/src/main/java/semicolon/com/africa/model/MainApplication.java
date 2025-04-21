package main.java.semicolon.com.africa.model;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainApplication {
    static Diaries diaries = new Diaries();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            String menu = JOptionPane.showInputDialog("""
                    Welcome to Eric Diary App
                    1  =>> Create a new diary
                    2  =>> Add an entry
                    3  =>> Find Entry by ID
                    4  =>> View entries
                    5  =>> Delete Entry
                    6  =>> Update Entry
                    7  =>> Find Diary
                    8 =>> Delete Diary
                    9 =>> Exit
                    """);

            switch (menu) {
                case "1":
                    createDiary();
                    break;
                case "2":
                    addEntryToADiary();
                    break;
                case "3":
                    findEntryById();
                    break;
                case "4":
                    viewEntries();
                    break;
                case "5":
                    deleteEntry();
                    break;
                case "6":
                    updateEntry();
                    break;
                case "7":
                    findDiary();
                    break;
                case "8":
                    deleteDiary();
                    break;
                case "9":
                    JOptionPane.showMessageDialog(null, "Exiting application. Goodbye!");
                    running = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

    public static void createDiary() {
        String name = JOptionPane.showInputDialog("Enter your username: ");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }
        String password = JOptionPane.showInputDialog("Create your password: ");
        if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
            return;
        }
        try {
            Diary myDiary = new Diary(name, password);
            diaries.addDiary(myDiary);
            JOptionPane.showMessageDialog(null, "Diary created successfully!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void addEntryToADiary() {
        String name = JOptionPane.showInputDialog("Enter your username: ");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
        }
        Diary diary = diaries.findByUserName(name);
        if (diary == null) {
            JOptionPane.showMessageDialog(null, "Diary not found for the given username.");
        }
        String password = JOptionPane.showInputDialog("Enter your password: ");
        if (!diary.unlockDiary(password)) {
            JOptionPane.showMessageDialog(null, "Invalid password.");
        }
        String id = JOptionPane.showInputDialog("Enter your entry ID: ");
        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID cannot be empty.");
        }
        String title = JOptionPane.showInputDialog("Enter your title: ");
        String body = JOptionPane.showInputDialog("What's the gist for today: ");
        try {
            Entry myEntry = new Entry(id, title, body);
            diary.createEntry(myEntry);
            JOptionPane.showMessageDialog(null, "Entry created successfully!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void findEntryById() {
        Diary diary = selectDiary();
        if (diary == null) return;
        String id = JOptionPane.showInputDialog("Enter the entry ID to find: ");
        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID cannot be empty.");
        }
        try {
            Entry entry = diary.findEntryById(id);
            if (entry != null) {
                JOptionPane.showMessageDialog(null, "Entry found: " + entry.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Entry not found.");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void viewEntries() {
        Diary diary = selectDiary();
        if (diary == null) return;
        try {
            List<Entry> entries = diary.getEntries();
            if (entries == null || entries.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No entries found in this diary.");
                return;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            StringBuilder entriesDisplay = new StringBuilder("Entries in the diary:\n\n");
            for (Entry entry : entries) {
                entriesDisplay.append("ID: ").append(entry.getId())
                        .append("\nTitle: ").append(entry.getTitle())
                        .append("\nBody: ").append(entry.getBody())
                        .append("\n\n");
            }

            JOptionPane.showMessageDialog(null, entriesDisplay.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving entries: " + e.getMessage());
        }
    }
    public static void deleteEntry() {
        Diary diary = selectDiary();
        if (diary == null) return;
        String id = JOptionPane.showInputDialog("Enter the ID of the entry to delete: ");
        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID cannot be empty.");
        }
        try {
            Entry entry = diary.findEntryById(id);
            if (entry != null) {
                diary.deleteEntry(entry);
                JOptionPane.showMessageDialog(null, "Entry deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Entry not found.");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void updateEntry() {
        Diary diary = selectDiary();
        if (diary == null) return;
        String id = JOptionPane.showInputDialog("Enter the ID of the entry to update: ");
        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID cannot be empty.");
        }
        String newTitle = JOptionPane.showInputDialog("Enter new title: ");
        String newBody = JOptionPane.showInputDialog("Enter new body: ");
        try {
            diary.updateEntry(id, newTitle, newBody);
            JOptionPane.showMessageDialog(null, "Entry updated successfully!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void findDiary() {
        String name = JOptionPane.showInputDialog("Enter your username to find your diary: ");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
        }
        Diary foundDiary = diaries.findByUserName(name);
        if (foundDiary != null) {
            JOptionPane.showMessageDialog(null, "Diary found for user: " + name);
        } else {
            JOptionPane.showMessageDialog(null, "Diary not found for the given username.");
        }
    }

    public static void deleteDiary() {
        String name = JOptionPane.showInputDialog("Enter your username to delete your diary: ");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        }
        try {
            diaries.delete(name);
            JOptionPane.showMessageDialog(null, "Diary deleted successfully!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    private static Diary selectDiary() {
        String name = JOptionPane.showInputDialog("Enter your username: ");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return null;
        }
        Diary diary = diaries.findByUserName(name);
        if (diary == null) {
            JOptionPane.showMessageDialog(null, "Diary not found for the given username.");
            return null;
        }
        String password = JOptionPane.showInputDialog("Enter your password: ");
        if (!diary.unlockDiary(password)) {
            JOptionPane.showMessageDialog(null, "Invalid password.");
            return null;
        }
        return diary;
    }
}

















