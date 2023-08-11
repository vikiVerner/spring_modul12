package com.example.todolist;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
public class NoteService {
    public static List<Note> notes = new ArrayList<>();

    public Note add(Note note) {
        Random random = new Random();
        note.setId(random.nextLong());
        notes.add(note);
        return note;
    }

    public List<Note> listAll() {
        return notes;
    }

    public void update(Note note) {
        int counter = 0;
        for (Note item : notes) {
            if (note.getId() == item.getId()) {
                item.setTitle(note.getTitle());
                item.setContent(note.getContent());
            } else {
                counter++;
            }
        }
        if (notes.size() == counter) {
            throw new IllegalArgumentException();
        }
    }

    public Note getById(long id) {
        int counter = 0;
        Note result = null;
        for (Note item : notes) {
            if (item.getId() == id) {
                result = item;
            } else {
                counter++;
            }
        }
        if (notes.size() == counter) {
            throw new IllegalArgumentException();
        }
        return result;
    }
    public void deleteById(long id){
        int counter = 0;
        List<Note> newList = new ArrayList<>();
        for (Note item : notes) {
            if (item.getId() != id) {
                newList.add(item);
            } else {
                counter++;
            }
        }
        notes.addAll(newList);
        if (counter==1) {
            throw new IllegalArgumentException();
        }
    }
}
