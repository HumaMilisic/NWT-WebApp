package com.example.repo;

import com.example.models.RelKorisnikXRelDokument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "relKorisnikXRelDokument",path = "relKorisnikXRelDokument")
public interface RelKorisnikXRelDokumentRepository extends PagingAndSortingRepository<RelKorisnikXRelDokument,Long> {

    @Override
    Iterable<RelKorisnikXRelDokument> findAll(Sort sort);

    @Override
    Page<RelKorisnikXRelDokument> findAll(Pageable pageable);

    @Override
    RelKorisnikXRelDokument save(RelKorisnikXRelDokument s);

    @Override
    RelKorisnikXRelDokument findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<RelKorisnikXRelDokument> findAll();

    @Override
    Iterable<RelKorisnikXRelDokument> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(RelKorisnikXRelDokument relKorisnikXRelDokument);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends RelKorisnikXRelDokument> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();
}
