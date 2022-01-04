package br.com.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.netflics.model.Critico;
import br.com.netflics.model.filter.FilterCritico;

import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import br.com.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class CriticoSpecificationHelper {

	public static Specification<Critico> fromId(Integer id, Tenant tenant) {
		return new Specification<Critico>() {
			@Override
			public Predicate toPredicate(Root<Critico> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Critico> filter(SearchParameters<FilterCritico> searchParam, Tenant tenant) {
		return new Specification<Critico>() {

			@Override
			public Predicate toPredicate(Root<Critico> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterCritico filterCritico = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterCritico.getNome() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("nome")), Util.wrapSufix(filterCritico.getNome().toUpperCase(), searchParam.isExact())));
				}  
				if (filterCritico.getEmpresa() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("empresa")), Util.wrapSufix(filterCritico.getEmpresa().toUpperCase(), searchParam.isExact())));
				}  
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
