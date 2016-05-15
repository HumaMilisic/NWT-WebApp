package com.example.repo;

import com.example.models.Akcija;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RepositoryRestResource(collectionResourceRel = "akcija",path = "akcija")
public interface AkcijaRepository extends PagingAndSortingRepository<Akcija,Long>{

    @Override
    Iterable<Akcija> findAll(Sort sort);

    @Override
    Page<Akcija> findAll(Pageable pageable);

    @Override
    Akcija save(Akcija s);


    @Override
    Akcija findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<Akcija> findAll();

    @Override
    Iterable<Akcija> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Akcija akcija);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends Akcija> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

    List<Akcija> findByNaziv(@Param("naziv")String naziv);

//    List<Akcija> findByDeleted(@Param("deleted")char deleted);
}
