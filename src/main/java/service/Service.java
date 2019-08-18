package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Service<H> {
    Iterable<H> findAll();

    Page<H> findAll(Pageable pageable);

    H findById(Integer id);

    void save(H h);

    void remove(Integer id);
}
