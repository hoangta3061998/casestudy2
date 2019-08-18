package service;

import model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteTypeService extends Service<NoteType> {
    Page<NoteType> findAllByNameContaining(String name, Pageable pageable);

    Iterable<NoteType> findAllByNameContaining(String name);
}
