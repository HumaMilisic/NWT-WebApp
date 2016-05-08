package com.example.repo;

import com.example.models.Komentar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by WorkIt on 27/03/2016.
 */
@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "komentar",path = "komentar")
public interface KomentarRepository extends PagingAndSortingRepository<Komentar,Long> {

    @Override
    Iterable<Komentar> findAll(Sort sort);

    @Override
    Page<Komentar> findAll(Pageable pageable);

    @Override
    Komentar save(Komentar s);

    @Override
    Komentar findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<Komentar> findAll();

    @Override
    Iterable<Komentar> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Komentar komentar);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends Komentar> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

    List<Komentar> findById(@Param("id")long id);
}
