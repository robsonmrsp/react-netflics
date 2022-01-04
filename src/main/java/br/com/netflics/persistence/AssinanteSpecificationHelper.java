package br.com.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.netflics.model.Assinante;
import br.com.netflics.model.filter.FilterAssinante;

import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import br.com.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class AssinanteSpecificationHelper {

	public static Specification<Assinante> fromId(Integer id, Tenant tenant) {
		return new Specification<Assinante>() {
			@Override
			public Predicate toPredicate(Root<Assinante> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Assinante> filter(SearchParameters<FilterAssinante> searchParam, Tenant tenant) {
		return new Specification<Assinante>() {

			@Override
			public Predicate toPredicate(Root<Assinante> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterAssinante filterAssinante = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterAssinante.getTelefone() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("telefone")), Util.wrapSufix(filterAssinante.getTelefone().toUpperCase(), searchParam.isExact())));
				}  
				if (filterAssinante.getDesconto() != null) {
					predicates.add(criteriaBuilder.equal(root.get("desconto"), filterAssinante.getDesconto()));
				}				
				if (filterAssinante.getDataVencimento() != null) {
					predicates.add(criteriaBuilder.equal(root.get("dataVencimento"), filterAssinante.getDataVencimento()));
				}				
				if (filterAssinante.getCpf() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("cpf")), Util.wrapSufix(filterAssinante.getCpf().toUpperCase(), searchParam.isExact())));
				}  
				if (filterAssinante.getNome() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("nome")), Util.wrapSufix(filterAssinante.getNome().toUpperCase(), searchParam.isExact())));
				}  
				if (filterAssinante.getValorMensalidade() != null) {
					predicates.add(criteriaBuilder.equal(root.get("valorMensalidade"), filterAssinante.getValorMensalidade()));
				}				
				if (filterAssinante.getObservacao() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("observacao")), Util.wrapSufix(filterAssinante.getObservacao().toUpperCase(), searchParam.isExact())));
				}  
				if (filterAssinante.getAtivo() != null) {
					predicates.add(criteriaBuilder.equal(root.get("ativo"), filterAssinante.getAtivo()));
				}				
				if (filterAssinante.getFoto() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("foto")), Util.wrapSufix(filterAssinante.getFoto().toUpperCase(), searchParam.isExact())));
				}  
				if (filterAssinante.getCelular() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("celular")), Util.wrapSufix(filterAssinante.getCelular().toUpperCase(), searchParam.isExact())));
				}  
				if (filterAssinante.getRg() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("rg")), Util.wrapSufix(filterAssinante.getRg().toUpperCase(), searchParam.isExact())));
				}  
				if (filterAssinante.getDataHoraAssinatura() != null) {
					predicates.add(criteriaBuilder.equal(root.get("dataHoraAssinatura"), filterAssinante.getDataHoraAssinatura()));
				}				
				if (filterAssinante.getDataNascimento() != null) {
					predicates.add(criteriaBuilder.equal(root.get("dataNascimento"), filterAssinante.getDataNascimento()));
				}				
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
