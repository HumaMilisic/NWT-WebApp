package com.example.repo;

import com.example.models.Korisnik;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RepositoryRestResource(collectionResourceRel = "korisnik",path = "korisnik")
public interface KorisnikRepository extends PagingAndSortingRepository<Korisnik,Long> {
    //List<Akcija> findByNaziv(@Param("name")String naziv);
    Korisnik findByUsername(@Param("username") String username);
    Korisnik findByEmail(@Param("email") String email);
}
