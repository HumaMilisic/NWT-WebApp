package com.example.repo.specifications;

import com.example.models.Dokument;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by WorkIt on 22/05/2016.
 */
public class DokumentSpecification implements Specification<Dokument> {

    @Override
    public Predicate toPredicate(Root<Dokument> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
