package com.example.repo;

import com.example.models.VrstaDokumenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "vrstaDokumenta",path = "vrstaDokumenta")
public interface VrstaDokumentaRepository extends PagingAndSortingRepository<VrstaDokumenta,Long> {

    @Override
    Iterable<VrstaDokumenta> findAll(Sort sort);

    @Override
    Page<VrstaDokumenta> findAll(Pageable pageable);

    @Override
    VrstaDokumenta save(VrstaDokumenta s);

    @Override
    VrstaDokumenta findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<VrstaDokumenta> findAll();

    @Override
    Iterable<VrstaDokumenta> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(VrstaDokumenta vrstaDokumenta);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends VrstaDokumenta> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

    List<VrstaDokumenta> findByNazivba(@Param("nazivba")String nazivba); //zasad po nazivu
}
