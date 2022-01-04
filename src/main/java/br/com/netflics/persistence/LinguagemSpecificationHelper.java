package br.com.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.netflics.model.Linguagem;
import br.com.netflics.model.filter.FilterLinguagem;

import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import br.com.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class LinguagemSpecificationHelper {

	public static Specification<Linguagem> fromId(Integer id, Tenant tenant) {
		return new Specification<Linguagem>() {
			@Override
			public Predicate toPredicate(Root<Linguagem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Linguagem> filter(SearchParameters<FilterLinguagem> searchParam, Tenant tenant) {
		return new Specification<Linguagem>() {

			@Override
			public Predicate toPredicate(Root<Linguagem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterLinguagem filterLinguagem = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterLinguagem.getNome() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("nome")), Util.wrapSufix(filterLinguagem.getNome().toUpperCase(), searchParam.isExact())));
				}  
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
