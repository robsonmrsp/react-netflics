package br.com.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.netflics.model.Classificacao;
import br.com.netflics.model.filter.FilterClassificacao;

import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import br.com.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class ClassificacaoSpecificationHelper {

	public static Specification<Classificacao> fromId(Integer id, Tenant tenant) {
		return new Specification<Classificacao>() {
			@Override
			public Predicate toPredicate(Root<Classificacao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Classificacao> filter(SearchParameters<FilterClassificacao> searchParam, Tenant tenant) {
		return new Specification<Classificacao>() {

			@Override
			public Predicate toPredicate(Root<Classificacao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterClassificacao filterClassificacao = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterClassificacao.getIdadeMinima() != null) {
					predicates.add(criteriaBuilder.equal(root.get("idadeMinima"), filterClassificacao.getIdadeMinima()));
				}				
				if (filterClassificacao.getNome() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("nome")), Util.wrapSufix(filterClassificacao.getNome().toUpperCase(), searchParam.isExact())));
				}  
				if (filterClassificacao.getDescricao() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("descricao")), Util.wrapSufix(filterClassificacao.getDescricao().toUpperCase(), searchParam.isExact())));
				}  
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
