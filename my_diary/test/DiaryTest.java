import main.java.semicolon.com.africa.model.Diaries;
import main.java.semicolon.com.africa.model.Diary;
import main.java.semicolon.com.africa.model.Entry;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiaryTest {
    @Test
    public void testDiaryIsLocked(){
        Diary diary = new Diary();
        diary.lockDiary();
        assertTrue(diary.isLocked());
    }
    @Test
    public void testDiaryIsNotLocked(){
        Diary diary = new Diary("Eric","1234");
        diary.lockDiary();
        diary.unlockDiary("1234");
        assertFalse(diary.isLocked());
    }
    @Test
    public void testthatEntryIsCreated(){
        Diary diary = new Diary("Eric","1234");
        diary.isLocked();
        diary.unlockDiary("1234");
        Entry myEntry = new Entry("4","baby","make una no fight ooo");
        diary.createEntry(myEntry);
        assertEquals(1,diary.getEntries().size());
    }
    @Test
    public void  testThatCreateTwoEntry(){
        Diary diary = new Diary("Eric","eric@312");
        diary.isLocked();
        diary.unlockDiary("eric@312");
        Entry myEntryOne = new Entry("23","baby","make una no fight ooo");
        Entry myEntryTwo = new Entry("12","My Peejay","I really love peejay");
        diary.createEntry(myEntryOne);
        diary.createEntry(myEntryTwo);
        assertEquals(2,diary.getEntries().size());
    }
    @Test
    public void testThatCreateEntries_andDelete1Entry(){
        Diary diary = new Diary("Eric","eric@312");
        diary.isLocked();
        diary.unlockDiary("eric@312");
        Entry myEntryOne = new Entry("23","baby","make una no fight ooo");
        Entry myEntryTwo = new Entry("12","miami","I really love the beach resort");
        Entry myEntryThree = new Entry("22","mary","Jesus is born");
        diary.createEntry(myEntryOne);
        diary.createEntry(myEntryTwo);
        diary.createEntry(myEntryThree);
        diary.deleteEntry(myEntryTwo);
        assertEquals(2,diary.getEntries().size());
    }
    @Test
    public void testThatCreates3Entries_andFind1Entry(){
        Diary diary = new Diary("Eric","1234");
        diary.lockDiary();
        diary.unlockDiary("1234");
        Entry myEntryOne = new Entry("12","Bahubali","The god of india");
        Entry myEntryTwo = new Entry("23","Wheel of time","The dragon reborn of the wetlanders");
        Entry myEntryThree = new Entry("67","Love is blind","can't u see");
        diary.createEntry(myEntryOne);
        diary.createEntry(myEntryTwo);
        diary.createEntry(myEntryThree);
        assertEquals(myEntryTwo,diary.findEntryById("23"));
    }
    @Test
    public void testThatDiaryIsFoundByUserName(){
        Diary diary = new Diary("Eric","eric@312");
        Diaries diaries = new Diaries();
        diary.isLocked();
        diary.unlockDiary("eric@312");
        Entry myEntryTwo = new Entry("12","My words","I really love words");
        diaries.addDiary(diary);
        diary.createEntry(myEntryTwo);
        assertEquals( diary, diaries.findByUserName("Eric"));
    }
    @Test
    public void testThatDiaryDeletedByUsername(){
        Diary diary1 = new Diary("aliyat","gotohe11");
        Diaries diaries = new Diaries();
        diary1.isLocked();
        diary1.unlockDiary("gotohe11");
        Diary diary2 = new Diary("rand","gotoce11");
        diary2.isLocked();
        diary2.unlockDiary("gotoce11");
        diaries.addDiary(diary1);
        diaries.addDiary(diary2);
        diaries.delete("aliyat");
        assertEquals(1,diaries.getDiaries().size());
    }
    @Test
    public void testThatDiaryUpdateEntry(){
        Diary diary = new Diary("monica","Ericali9");
        diary.isLocked();
        diary.unlockDiary("Ericali9");
        Entry myEntry = new Entry("34","Atomic Habits","The atoms of habits");
        diary.createEntry(myEntry);
        diary.updateEntry("34","Atomic Habit","NeverMind");
        assertEquals("Atomic Habit",myEntry.getTitle());
        assertEquals("NeverMind",myEntry.getBody());
    }
    @Test
    public void testThatDiaryUpdateTwice(){
        Diary diary= new Diary("Xavier","west123");
        diary.isLocked();
        diary.unlockDiary("west123");
        Entry myEntry = new Entry ("32","Best Friends","Always there for eachother");
        diary.createEntry(myEntry);
        diary.updateEntry("32","Friends for life","u sure about that?");
        diary.updateEntry("32","Undefined","it is empty oooh");
        assertEquals("Undefined",myEntry.getTitle());
        assertEquals("it is empty oooh",myEntry.getBody());
    }

}
