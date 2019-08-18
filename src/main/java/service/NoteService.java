package service;

import model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService extends Service<Note> {
    Page<Note> findAllByTitleContaining(String name, Pageable pageable);
    Iterable<Note> findAllByTitleContaining(String name);
    Page<Note> findAllByTypeId(Integer id, Pageable pageable);
    Iterable<Note> findAllByTypeId(Integer id);
}
