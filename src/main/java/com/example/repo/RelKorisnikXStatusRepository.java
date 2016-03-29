package com.example.repo;

import com.example.models.RelKorisnikXStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "relKorisnikXStatus",path = "relKorisnikXStatus")
public interface RelKorisnikXStatusRepository extends PagingAndSortingRepository<RelKorisnikXStatus,Long> {

    @Override
    Iterable<RelKorisnikXStatus> findAll(Sort sort);

    @Override
    Page<RelKorisnikXStatus> findAll(Pageable pageable);

    @Override
    RelKorisnikXStatus save(RelKorisnikXStatus s);

    @Override
    RelKorisnikXStatus findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<RelKorisnikXStatus> findAll();

    @Override
    Iterable<RelKorisnikXStatus> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(RelKorisnikXStatus relKorisnikXStatus);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends RelKorisnikXStatus> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();
}
