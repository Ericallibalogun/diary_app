package main.java.semicolon.com.africa.model;

import java.util.ArrayList;
import java.util.List;

public class Diaries {

    private List<Diary> diaries;

    public Diaries(){
        this.diaries = new ArrayList<>();
    }
    public void addDiary(Diary diary) {
        diaries.add(diary);
    }
    public Diary findByUserName(String userName) {
        for (Diary diary : diaries) {
            if(diary.getUsername().equals(userName)) {
                return diary;
            }
        }
        return null;
    }
    public void delete(String username) {
        for(Diary diary : diaries){
            if(diary.getUsername().equals(username)){
               diaries.remove(diary);
               break;
            }
        }
    }
    public List<Diary> getDiaries(){
        return diaries;
    }

}
