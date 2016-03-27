package com.example.repo;

import com.example.models.Notifikacija;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "notifikacija",path = "notifikacija")
public interface NotifikacijaRepository extends PagingAndSortingRepository<Notifikacija,Long> {

    @Override
    Iterable<Notifikacija> findAll(Sort sort);

    @Override
    Page<Notifikacija> findAll(Pageable pageable);

    @Override
    Notifikacija save(Notifikacija s);

    @Override
    Notifikacija findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<Notifikacija> findAll();

    @Override
    Iterable<Notifikacija> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Notifikacija notifikacija);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends Notifikacija> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

    List<Notifikacija> findById(@Param("id")long id); //zasad po id


}
