package com.example.repo;

import com.example.models.RelKorisnikXVrstaDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "relKorisnikXVrstaDoc",path = "relKorisnikXVrstaDoc")
public interface RelKorisnikXVrstaDocRepository extends PagingAndSortingRepository<RelKorisnikXVrstaDoc,Long> {

    @Override
    Iterable<RelKorisnikXVrstaDoc> findAll(Sort sort);

    @Override
    Page<RelKorisnikXVrstaDoc> findAll(Pageable pageable);

    @Override
    RelKorisnikXVrstaDoc save(RelKorisnikXVrstaDoc s);

    @Override
    RelKorisnikXVrstaDoc findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<RelKorisnikXVrstaDoc> findAll();

    @Override
    Iterable<RelKorisnikXVrstaDoc> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(RelKorisnikXVrstaDoc relKorisnikXVrstaDoc);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends RelKorisnikXVrstaDoc> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

}
