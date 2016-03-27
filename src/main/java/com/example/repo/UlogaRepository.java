package com.example.repo;

import com.example.models.Uloga;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by WorkIt on 25/03/2016.
 */
public interface UlogaRepository extends PagingAndSortingRepository<Uloga,Long> {
}
