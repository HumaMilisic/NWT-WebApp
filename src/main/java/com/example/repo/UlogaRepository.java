package com.example.repo;

import com.example.models.Uloga;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "uloga",path = "uloga")
public interface UlogaRepository extends PagingAndSortingRepository<Uloga,Long> {


    List<Uloga> findByNaziv(@Param("naziv")String naziv);

    List<Uloga> findAllByKorisnikSet_Username(@Param("username") String username);
}
