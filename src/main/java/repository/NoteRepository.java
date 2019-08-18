package repository;

import model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note, Integer> {
    Page<Note> findAllByTitleContaining(String name, Pageable pageable);
    Iterable<Note> findAllByTitleContaining(String name);
    Page<Note> findAllByTypeId(Integer id, Pageable pageable);
    Iterable<Note> findAllByTypeId(Integer id);
}
