package com.example.repo;

import com.example.models.Dogadjaj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "dogadjaj",path = "dogadjaj")
public interface DogadjajRepository extends PagingAndSortingRepository<Dogadjaj,Long> {//T,ID

    @Override
    Iterable<Dogadjaj> findAll(Sort sort);

    @Override
    Page<Dogadjaj> findAll(Pageable pageable);

    @Override
    Dogadjaj save(Dogadjaj s);

//    @Override
//    Iterable<Dogadjaj> save(Iterable<Dogadjaj> iterable);

    @Override
    Dogadjaj findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<Dogadjaj> findAll();

    @Override
    Iterable<Dogadjaj> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    void delete(Long aLong);

    @Override
    void delete(Dogadjaj dogadjaj);

    @Override
    void delete(Iterable<? extends Dogadjaj> iterable);

    @Override
    void deleteAll();

    List<Dogadjaj> findByNaziv(@Param("name") String naziv);


}
