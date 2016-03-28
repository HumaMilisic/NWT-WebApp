package com.example.repo;

import com.example.models.RelKorisnikXNotifikacija;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "relKorisnikXNotifikacija",path = "relKorisnikXNotifikacija")
public interface RelKorisnikXNotifikacijaRepository extends PagingAndSortingRepository<RelKorisnikXNotifikacija,Long> {

    @Override
    Iterable<RelKorisnikXNotifikacija> findAll(Sort sort);

    @Override
    Page<RelKorisnikXNotifikacija> findAll(Pageable pageable);

    @Override
    RelKorisnikXNotifikacija save(RelKorisnikXNotifikacija s);

    @Override
    RelKorisnikXNotifikacija findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<RelKorisnikXNotifikacija> findAll();

    @Override
    Iterable<RelKorisnikXNotifikacija> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(RelKorisnikXNotifikacija relKorisnikXNotifikacija);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends RelKorisnikXNotifikacija> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

}
