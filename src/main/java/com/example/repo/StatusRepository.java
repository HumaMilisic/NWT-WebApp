package com.example.repo;

import com.example.models.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "status",path = "status")
public interface StatusRepository extends PagingAndSortingRepository<Status,Long> {

    @Override
    Iterable<Status> findAll(Sort sort);

    @Override
    Page<Status> findAll(Pageable pageable);

    @Override
    Status save(Status s); //warning

    @Override
    Status findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<Status> findAll();

    @Override
    Iterable<Status> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Status status);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends Status> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

    List<Status> findByNazivba(@Param("nazivba")String nazivba); //zasad po nazivu

}
