package repository;

import model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteTypeRepository extends PagingAndSortingRepository<NoteType, Integer> {
    Page<NoteType> findAllByNameContaining(String name, Pageable pageable);
    Iterable<NoteType> findAllByNameContaining(String name);
}
