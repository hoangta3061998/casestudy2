package service.impl;

import model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.NoteRepository;
import service.NoteService;

public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Override
    public Page<Note> findAllByTitleContaining(String name, Pageable pageable) {
        return noteRepository.findAllByTitleContaining(name, pageable);
    }

    @Override
    public Iterable<Note> findAllByTitleContaining(String name) {
        return noteRepository.findAllByTitleContaining(name);
    }

    @Override
    public Page<Note> findAllByTypeId(Integer id, Pageable pageable) {
        return noteRepository.findAllByTypeId(id, pageable);
    }

    @Override
    public Iterable<Note> findAllByTypeId(Integer id) {
        return noteRepository.findAllByTypeId(id);
    }

    @Override
    public Iterable<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Page<Note> findAll(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public Note findById(Integer id) {
        return noteRepository.findOne(id);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void remove(Integer id) {
        noteRepository.delete(id);
    }
}
