package br.com.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.netflics.model.Visualizacao;
import br.com.netflics.model.filter.FilterVisualizacao;

import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import br.com.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class VisualizacaoSpecificationHelper {

	public static Specification<Visualizacao> fromId(Integer id, Tenant tenant) {
		return new Specification<Visualizacao>() {
			@Override
			public Predicate toPredicate(Root<Visualizacao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Visualizacao> filter(SearchParameters<FilterVisualizacao> searchParam, Tenant tenant) {
		return new Specification<Visualizacao>() {

			@Override
			public Predicate toPredicate(Root<Visualizacao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterVisualizacao filterVisualizacao = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterVisualizacao.getDataHora() != null) {
					predicates.add(criteriaBuilder.equal(root.get("dataHora"), filterVisualizacao.getDataHora()));
				}				
				if (filterVisualizacao.getPercentualAssistido() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("percentualAssistido")), Util.wrapSufix(filterVisualizacao.getPercentualAssistido().toUpperCase(), searchParam.isExact())));
				}  
				if (filterVisualizacao.getFilme() != null) {
					predicates.add(criteriaBuilder.equal(root.get("filme").get("id"), filterVisualizacao.getFilme()));
				}
				if (filterVisualizacao.getAssinante() != null) {
					predicates.add(criteriaBuilder.equal(root.get("assinante").get("id"), filterVisualizacao.getAssinante()));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
