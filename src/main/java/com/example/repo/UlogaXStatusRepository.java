package com.example.repo;

import com.example.models.Notifikacija;
import com.example.models.UlogaXNotifikacija;
import com.example.models.UlogaXStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by monta on 3/29/2016.
 */

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "UlogaXStatus",path = "UlogaXStatus")
public interface UlogaXStatusRepository extends PagingAndSortingRepository<UlogaXStatus,Long> {

    @Override
    Iterable<UlogaXStatus> findAll(Sort sort);

    @Override
    Page<UlogaXStatus> findAll(Pageable pageable);

    @Override
    UlogaXStatus save(UlogaXStatus s);

    @Override
    UlogaXStatus findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<UlogaXStatus> findAll();

    @Override
    Iterable<UlogaXStatus> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(UlogaXStatus ulogaXStatus);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Iterable<? extends UlogaXStatus> iterable);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAll();

    List<UlogaXStatus> findById(@Param("id")long id); //zasad po id

    //   List<UlogaXStatus> findByTekst(@Param("tekst")String tekst);



}
