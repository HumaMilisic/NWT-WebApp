package com.example.repo;

import com.example.models.Komentar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by WorkIt on 27/03/2016.
 */
@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "komentar",path = "komentar")
public interface KomentarRepository extends PagingAndSortingRepository<Komentar,Long> {
}
