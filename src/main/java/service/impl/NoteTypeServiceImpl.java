package service.impl;

import model.NoteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.NoteTypeRepository;
import service.NoteTypeService;

public class NoteTypeServiceImpl implements NoteTypeService {

    @Autowired
    NoteTypeRepository noteTypeRepository;

    @Override
    public Page<NoteType> findAllByNameContaining(String name, Pageable pageable) {
        return noteTypeRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Iterable<NoteType> findAllByNameContaining(String name) {
        return noteTypeRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<NoteType> findAll() {
        return noteTypeRepository.findAll();
    }

    @Override
    public Page<NoteType> findAll(Pageable pageable) {
        return noteTypeRepository.findAll(pageable);
    }

    @Override
    public NoteType findById(Integer id) {
        return noteTypeRepository.findOne(id);
    }

    @Override
    public void save(NoteType noteType) {
        noteTypeRepository.save(noteType);
    }

    @Override
    public void remove(Integer id) {
        noteTypeRepository.delete(id);
    }
}
