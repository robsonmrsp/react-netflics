package br.com.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.netflics.model.Ator;
import br.com.netflics.model.filter.FilterAtor;

import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import br.com.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class AtorSpecificationHelper {

	public static Specification<Ator> fromId(Integer id, Tenant tenant) {
		return new Specification<Ator>() {
			@Override
			public Predicate toPredicate(Root<Ator> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Ator> filter(SearchParameters<FilterAtor> searchParam, Tenant tenant) {
		return new Specification<Ator>() {

			@Override
			public Predicate toPredicate(Root<Ator> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterAtor filterAtor = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterAtor.getBiografia() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("biografia")), Util.wrapSufix(filterAtor.getBiografia().toUpperCase(), searchParam.isExact())));
				}  
				if (filterAtor.getDataNascimento() != null) {
					predicates.add(criteriaBuilder.equal(root.get("dataNascimento"), filterAtor.getDataNascimento()));
				}				
				if (filterAtor.getNome() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("nome")), Util.wrapSufix(filterAtor.getNome().toUpperCase(), searchParam.isExact())));
				}  
				if (filterAtor.getFoto() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("foto")), Util.wrapSufix(filterAtor.getFoto().toUpperCase(), searchParam.isExact())));
				}  
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
