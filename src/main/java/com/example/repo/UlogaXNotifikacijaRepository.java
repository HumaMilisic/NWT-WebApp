package com.example.repo;

import com.example.models.UlogaXNotifikacija;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "ulogaXNotifikacija",path = "ulogaXNotifikacija")
public interface UlogaXNotifikacijaRepository extends PagingAndSortingRepository<UlogaXNotifikacija,Long> {

    @Override
    Iterable<UlogaXNotifikacija> findAll(Sort sort);

    @Override
    Page<UlogaXNotifikacija> findAll(Pageable pageable);

    @Override
    UlogaXNotifikacija save(UlogaXNotifikacija s);

    @Override
    UlogaXNotifikacija findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<UlogaXNotifikacija> findAll();

    @Override
    Iterable<UlogaXNotifikacija> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(UlogaXNotifikacija ulogaXNotifikacija);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends UlogaXNotifikacija> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();
    

}
