package com.example.repo;

import com.example.models.UlogaXVrstaDokumenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "ulogaXVrstaDokumenta",path = "ulogaXVrstaDokumenta")
public interface UlogaXVrstaDokumentaRepository extends PagingAndSortingRepository<UlogaXVrstaDokumenta,Long> {

    @Override
    Iterable<UlogaXVrstaDokumenta> findAll(Sort sort);

    @Override
    Page<UlogaXVrstaDokumenta> findAll(Pageable pageable);

    @Override
    UlogaXVrstaDokumenta save(UlogaXVrstaDokumenta s);

    @Override
    UlogaXVrstaDokumenta findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<UlogaXVrstaDokumenta> findAll();

    @Override
    Iterable<UlogaXVrstaDokumenta> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(UlogaXVrstaDokumenta ulogaXVrstaDokumenta);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends UlogaXVrstaDokumenta> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();


}
