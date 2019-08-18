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

import java.util.Optional;

@Controller
public class NoteTypeController {

    @Autowired
    NoteTypeService noteTypeService;

    @Autowired
    NoteService noteService;

    @GetMapping("/noteTypes")
    public ModelAndView listNoteTypes(@RequestParam("s") Optional<String> s, @PageableDefault(10) Pageable pageable){
        Page<NoteType> noteTypes;
        if(s.isPresent()){
            noteTypes = noteTypeService.findAllByNameContaining(s.get(), pageable);
        } else {
            noteTypes = noteTypeService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/noteType/list");
        modelAndView.addObject("noteTypes", noteTypes);
        return modelAndView;
    }

    @GetMapping("/create-noteType")
    public ModelAndView showCreateNoteTypeForm(){
        ModelAndView modelAndView = new ModelAndView("/noteType/create");
        modelAndView.addObject("noteType", new NoteType());
        return modelAndView;
    }

    @PostMapping("/create-noteType")
    public ModelAndView saveNoteType(@Validated @ModelAttribute("noteType") NoteType noteType, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("/noteType/create");
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        }
        noteTypeService.save(noteType);
        modelAndView.addObject("noteType", new NoteType());
        modelAndView.addObject("message", "New noteType created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-noteType/{id}")
    public ModelAndView showEditNoteTypeForm(@PathVariable Integer id){
        NoteType noteType = noteTypeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/noteType/edit");;
        if(noteType != null) {
            modelAndView.addObject("noteType", noteType);
        }else {
            modelAndView.addObject("message", "NoteType ID not exist");
        }
        return modelAndView;
    }

    @PostMapping("/edit-noteType")
    public ModelAndView updateNoteType(@Validated @ModelAttribute("noteType") NoteType noteType, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("/noteType/edit");
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        }
        noteTypeService.save(noteType);
        modelAndView.addObject("noteType", noteType);
        modelAndView.addObject("message", "NoteType updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-noteType/{id}")
    public ModelAndView showDeleteNoteTypeForm(@PathVariable Integer id){
        NoteType noteType = noteTypeService.findById(id);
        if(noteType != null) {
            ModelAndView modelAndView = new ModelAndView("/noteType/delete");
            modelAndView.addObject("noteType", noteType);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

    @PostMapping("/delete-noteType")
    public String deleteNoteType(@ModelAttribute("noteType") NoteType noteType){
        noteTypeService.remove(noteType.getId());
        return "redirect:noteTypes";
    }

    @GetMapping("/view-noteType/{id}")
    public ModelAndView viewNoteType(@PathVariable("id") Integer id, @PageableDefault(10) Pageable pageable){
        NoteType noteType = noteTypeService.findById(id);
        Page<Note> notes = noteService.findAllByTypeId(id, pageable);
        if(noteType == null){
            return new ModelAndView("/error");
        }
        ModelAndView modelAndView = new ModelAndView("/noteType/view");
        modelAndView.addObject("noteType", noteType);
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }
}
