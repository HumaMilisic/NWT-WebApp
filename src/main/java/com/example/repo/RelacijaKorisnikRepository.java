package com.example.repo;

import com.example.models.RelacijaKorisnik;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "relacijaKorisnik",path = "relacijaKorisnik")
public interface RelacijaKorisnikRepository extends PagingAndSortingRepository<RelacijaKorisnik,Long> {

    @Override
    Iterable<RelacijaKorisnik> findAll(Sort sort);

    @Override
    Page<RelacijaKorisnik> findAll(Pageable pageable);

    @Override
    RelacijaKorisnik save(RelacijaKorisnik s);

    @Override
    RelacijaKorisnik findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<RelacijaKorisnik> findAll();

    @Override
    Iterable<RelacijaKorisnik> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(RelacijaKorisnik relacijaKorisnik);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends RelacijaKorisnik> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

    List<RelacijaKorisnik> findById(@Param("id")long id); //zasad po id

    List<RelacijaKorisnik> findByNazivba(@Param("nazivba")long nazivba); //po nazivu BA
}
