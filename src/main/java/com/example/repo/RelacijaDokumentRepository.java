package com.example.repo;

import com.example.models.RelacijaDokument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "relacijaDokument",path = "relacijaDokument")
public interface RelacijaDokumentRepository extends PagingAndSortingRepository<RelacijaDokument,Long> {

    @Override
    Iterable<RelacijaDokument> findAll(Sort sort);

    @Override
    Page<RelacijaDokument> findAll(Pageable pageable);

    @Override
    RelacijaDokument save(RelacijaDokument s);

    @Override
    RelacijaDokument findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<RelacijaDokument> findAll();

    @Override
    Iterable<RelacijaDokument> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(RelacijaDokument relacijaDokument);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends RelacijaDokument> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

    List<RelacijaDokument> findById(@Param("id")long id); //zasad po id
}
