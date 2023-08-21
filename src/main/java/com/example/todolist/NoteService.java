package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
@Service
public class NoteService {
    public NoteRepository repository;
    @Autowired
    public void SetNoteRepository(NoteRepository repository){
        this.repository = repository;
    }
    public Note add(Note note) {
        Random random = new Random();
        note.setId(random.nextInt(1000000));
        return repository.save(note);
    }

    public List<Note> listAll() {

        return repository.findAll();
    }

    public Note getById(long id) {
        if(!repository.existsById(id)){
            throw new IllegalArgumentException();
        }
        return repository.findById(id).get();
    }
    public void deleteById(long id){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException();
        }
        repository.deleteById(id);
    }
    public void update(Note note) {
        if(!repository.existsById(note.getId())){
            throw new IllegalArgumentException();
        }
        repository.save(note);

    }
}

