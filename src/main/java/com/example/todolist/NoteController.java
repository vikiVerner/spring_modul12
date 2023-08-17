package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(value="/note")
@RestController
public class NoteController {
  private NoteService noteService;
    @Autowired
    public void SetNoteService(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping(value = "/list")
    public ModelAndView listAll() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("notes",noteService.listAll());
            return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView createNote(){
        return new ModelAndView("create");
    }
   @PostMapping(value = "/create")
    public void createNote(@RequestParam(name = "title") String title,
                                   @RequestParam(name = "content") String content, HttpServletResponse http ) throws IOException {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteService.add(note);
       http.sendRedirect("/note/list");
    }
    @GetMapping(value = "/delete/{id}")
    public void deleteNote(@PathVariable ("id") long id, HttpServletResponse http) throws IOException {
        noteService.deleteById(id);
        http.sendRedirect("/note/list");
    }

    @GetMapping(value = "/edit")
    public ModelAndView editNote(@RequestParam ("id") long id){
        Note byId = noteService.getById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject(byId);
        return modelAndView;

    }
    @PostMapping(value ="/edit")
    public void editNote(@RequestParam(name = "id") long id,
                         @RequestParam(name = "title") String title,
                         @RequestParam(name = "content") String content, HttpServletResponse http) throws IOException {
        Note note = new Note();
        note.setId(id);
        note.setContent(content);
        note.setTitle(title);
        noteService.update(note);
        http.sendRedirect("/note/list");
    }
}
