package com.example.todolist;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {
    public NoteRepository repository;
    public NoteService(NoteRepository repository){
        this.repository = repository;
    }
    public Note add(Note note) {
        return repository.save(note);
    }

    public List<Note> listAll() {
        return  repository.findAll();
    }

    public Note getById(long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
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

