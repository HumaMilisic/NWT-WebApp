package com.example.repo;

import com.example.models.Dokument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "dokument",path = "dokument")
public interface DokumentRepository extends PagingAndSortingRepository<Dokument,Long> {

    @Override
//    @PostFilter("filterObject.username == authentication.name or hasRole('ROLE_ADMIN')")
//    @PostFilter("filterObject.username == authentication.name")
    Page<Dokument> findAll(Pageable pageable);

    @Override
//    @PostFilter("filterObject.username == authentication.name")
//    @PostFilter("filterObject.username == authentication.name or hasRole('ROLE_ADMIN')")
    Iterable<Dokument> findAll(Sort sort);

//    @Override
//    Iterable<Dokument> findAll(Sort sort);
//
//    @Override
//    Page<Dokument> findAll(Pageable pageable);

    @Override
    Dokument save(Dokument s);

//    @Override
//    <S extends T> Iterable<S> save(Iterable<S> iterable);

    @Override
    Dokument findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<Dokument> findAll();

    @Override
    Iterable<Dokument> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    void delete(Long aLong);

    @Override
    void delete(Dokument dokument);

    @Override
    void delete(Iterable<? extends Dokument> iterable);

    @Override
    void deleteAll();

    List<Dokument> findByOznaka(@Param("oznaka")long oznaka);
}
