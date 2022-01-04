package br.com.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.netflics.model.Genero;
import br.com.netflics.model.filter.FilterGenero;

import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import br.com.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class GeneroSpecificationHelper {

	public static Specification<Genero> fromId(Integer id, Tenant tenant) {
		return new Specification<Genero>() {
			@Override
			public Predicate toPredicate(Root<Genero> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Genero> filter(SearchParameters<FilterGenero> searchParam, Tenant tenant) {
		return new Specification<Genero>() {

			@Override
			public Predicate toPredicate(Root<Genero> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterGenero filterGenero = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterGenero.getNome() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("nome")), Util.wrapSufix(filterGenero.getNome().toUpperCase(), searchParam.isExact())));
				}  
				if (filterGenero.getDescricao() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("descricao")), Util.wrapSufix(filterGenero.getDescricao().toUpperCase(), searchParam.isExact())));
				}  
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
