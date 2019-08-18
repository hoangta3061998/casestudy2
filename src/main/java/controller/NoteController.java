package controller;

import model.Note;
import model.NoteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.NoteService;
import service.NoteTypeService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    NoteTypeService noteTypeService;

    @ModelAttribute("noteTypes")
    public Iterable<NoteType> departments(){return noteTypeService.findAll();}

    @GetMapping("/notes")
    public ModelAndView listNotes(@RequestParam("s") Optional<String> s, @PageableDefault(10) Pageable pageable){
        Page<Note> notes;
        if(s.isPresent()){
            notes = noteService.findAllByTitleContaining(s.get(), pageable);
        } else {
            notes = noteService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/note/list");
        modelAndView.addObject("notes", notes);
        modelAndView.addObject("noteTypeService", noteTypeService);
        return modelAndView;
    }

    @GetMapping("/create-note")
    public ModelAndView showCreateNoteForm(){
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note", new Note());
        return modelAndView;
    }

    @PostMapping("/create-note")
    public ModelAndView saveNote(@Valid @ModelAttribute("note") Note note, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("/note/create");
        new Note().validate(note, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        }
        noteService.save(note);
        modelAndView.addObject("note", new Note());
        modelAndView.addObject("message", "New note created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-note/{id}")
    public ModelAndView showEditNoteForm(@PathVariable Integer id){
        Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/note/edit");;
        if(note != null) {
            modelAndView.addObject("note", note);
        }else {
            modelAndView.addObject("message", "Note ID not exist");
        }
        return modelAndView;
    }

    @PostMapping("/edit-note")
    public ModelAndView updateNote(@Validated @ModelAttribute("note") Note note, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("/note/edit");
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        }
        noteService.save(note);
        modelAndView.addObject("note", note);
        modelAndView.addObject("message", "Note updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-note/{id}")
    public ModelAndView showDeleteNoteForm(@PathVariable Integer id){
        Note note = noteService.findById(id);
        if(note != null) {
            ModelAndView modelAndView = new ModelAndView("/note/delete");
            modelAndView.addObject("note", note);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

    @PostMapping("/delete-note")
    public String deleteNote(@ModelAttribute("note") Note note){
        noteService.remove(note.getId());
        return "redirect:notes";
    }

    @GetMapping("/view-note/{id}")
    public ModelAndView viewNote(@PathVariable("id") Integer id){
        Note note = noteService.findById(id);
        if(note == null){
            return new ModelAndView("/error");
        }
        ModelAndView modelAndView = new ModelAndView("/note/view");
        modelAndView.addObject("note", note);
        if (noteTypeService!=null) {
            if (note.getTypeId()!=null) {
                NoteType noteType = noteTypeService.findById(note.getTypeId());
                modelAndView.addObject("noteType", noteType);
            }
        }
        return modelAndView;
    }
}
