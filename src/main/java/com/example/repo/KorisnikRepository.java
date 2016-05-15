package com.example.repo;

import com.example.models.Korisnik;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

//@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "korisnik",path = "korisnik")
public interface KorisnikRepository extends PagingAndSortingRepository<Korisnik,Long> {
    //List<Akcija> findByNaziv(@Param("name")String naziv);

    Korisnik findByUsername(@Param("username") String username);


    Korisnik findByEmail(@Param("email") String email);

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    Iterable<Korisnik> findAll(Sort sort);

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    Page<Korisnik> findAll(Pageable pageable);

    @Override
//    @PreAuthorize("hasRole('ROLE_USER')")
    Korisnik save(Korisnik s);

//    @Override
//    Iterable<Korisnik> save(Iterable<Korisnik> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    Korisnik findOne(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    boolean exists(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    Iterable<Korisnik> findAll();

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    Iterable<Korisnik> findAll(Iterable<Long> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Korisnik korisnik);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends Korisnik> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();
}
