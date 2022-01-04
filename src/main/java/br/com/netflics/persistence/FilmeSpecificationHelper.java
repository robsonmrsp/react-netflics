package br.com.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.netflics.model.Filme;
import br.com.netflics.model.filter.FilterFilme;

import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import br.com.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class FilmeSpecificationHelper {

	public static Specification<Filme> fromId(Integer id, Tenant tenant) {
		return new Specification<Filme>() {
			@Override
			public Predicate toPredicate(Root<Filme> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Filme> filter(SearchParameters<FilterFilme> searchParam, Tenant tenant) {
		return new Specification<Filme>() {

			@Override
			public Predicate toPredicate(Root<Filme> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterFilme filterFilme = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterFilme.getTituloOriginal() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("tituloOriginal")), Util.wrapSufix(filterFilme.getTituloOriginal().toUpperCase(), searchParam.isExact())));
				}  
				if (filterFilme.getPoster() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("poster")), Util.wrapSufix(filterFilme.getPoster().toUpperCase(), searchParam.isExact())));
				}  
				if (filterFilme.getDuracao() != null) {
					predicates.add(criteriaBuilder.equal(root.get("duracao"), filterFilme.getDuracao()));
				}				
				if (filterFilme.getDataLancamento() != null) {
					predicates.add(criteriaBuilder.equal(root.get("dataLancamento"), filterFilme.getDataLancamento()));
				}				
				if (filterFilme.getSinopse() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("sinopse")), Util.wrapSufix(filterFilme.getSinopse().toUpperCase(), searchParam.isExact())));
				}  
				if (filterFilme.getTitulo() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("titulo")), Util.wrapSufix(filterFilme.getTitulo().toUpperCase(), searchParam.isExact())));
				}  
				if (filterFilme.getDiretor() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("diretor")), Util.wrapSufix(filterFilme.getDiretor().toUpperCase(), searchParam.isExact())));
				}  
				if (filterFilme.getClassificacao() != null) {
					predicates.add(criteriaBuilder.equal(root.get("classificacao").get("id"), filterFilme.getClassificacao()));
				}
				if (filterFilme.getLinguagem() != null) {
					predicates.add(criteriaBuilder.equal(root.get("linguagem").get("id"), filterFilme.getLinguagem()));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
