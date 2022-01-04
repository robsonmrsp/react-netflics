package br.com.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.netflics.model.Avaliacao;
import br.com.netflics.model.filter.FilterAvaliacao;

import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import br.com.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class AvaliacaoSpecificationHelper {

	public static Specification<Avaliacao> fromId(Integer id, Tenant tenant) {
		return new Specification<Avaliacao>() {
			@Override
			public Predicate toPredicate(Root<Avaliacao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Avaliacao> filter(SearchParameters<FilterAvaliacao> searchParam, Tenant tenant) {
		return new Specification<Avaliacao>() {

			@Override
			public Predicate toPredicate(Root<Avaliacao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterAvaliacao filterAvaliacao = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterAvaliacao.getDataHora() != null) {
					predicates.add(criteriaBuilder.equal(root.get("dataHora"), filterAvaliacao.getDataHora()));
				}				
				if (filterAvaliacao.getFilme() != null) {
					predicates.add(criteriaBuilder.equal(root.get("filme").get("id"), filterAvaliacao.getFilme()));
				}
				if (filterAvaliacao.getCritico() != null) {
					predicates.add(criteriaBuilder.equal(root.get("critico").get("id"), filterAvaliacao.getCritico()));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
