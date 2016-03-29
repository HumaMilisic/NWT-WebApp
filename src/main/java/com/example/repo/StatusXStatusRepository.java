package com.example.repo;

import com.example.models.StatusXStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "statusXStatus",path = "statusXStatus")
public interface StatusXStatusRepository extends PagingAndSortingRepository<StatusXStatus,Long> {

    @Override
    Iterable<StatusXStatus> findAll(Sort sort);

    @Override
    Page<StatusXStatus> findAll(Pageable pageable);

    @Override
    StatusXStatus save(StatusXStatus s);

    @Override
    StatusXStatus findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<StatusXStatus> findAll();

    @Override
    Iterable<StatusXStatus> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(StatusXStatus statusXStatus);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends StatusXStatus> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();
}
