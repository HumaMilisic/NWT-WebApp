//package com.example.repo;
//
//import com.example.models.Korisnik;
//import com.example.models.KorisnikXKorisnik;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.security.access.prepost.PreAuthorize;
//
//import java.util.List;
//
//@PreAuthorize("hasRole('ROLE_USER')")
//@RepositoryRestResource(collectionResourceRel = "korisnikXkorisnik",path = "korisnikXkorisnik")
//public interface KorisnikXKorisnikRepository extends PagingAndSortingRepository<KorisnikXKorisnik,Long> {
//
//    @Override
//    Iterable<KorisnikXKorisnik> findAll(Sort sort);
//
//    @Override
//    Page<KorisnikXKorisnik> findAll(Pageable pageable);
//
//    @Override
//    KorisnikXKorisnik save(KorisnikXKorisnik s);
//
//    @Override
//    KorisnikXKorisnik findOne(Long aLong);
//
//    @Override
//    boolean exists(Long aLong);
//
//    @Override
//    Iterable<KorisnikXKorisnik> findAll();
//
//    @Override
//    Iterable<KorisnikXKorisnik> findAll(Iterable<Long> iterable);
//
//    @Override
//    long count();
//
//    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    void delete(Long aLong);
//
//    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    void delete(KorisnikXKorisnik korisnikXkorisnik);
//
//    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    void delete(Iterable<? extends KorisnikXKorisnik> iterable);
//
//    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    void deleteAll();
//
//}
