package br.com.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.netflics.model.Permission;
import br.com.netflics.model.filter.FilterPermission;

import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("serial")
public class PermissionSpecificationHelper {

	public static Specification<Permission> filter(SearchParameters<FilterPermission> searchParam) {
		return new Specification<Permission>() {

			@Override
			public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterPermission filterPermission = searchParam.getFilter();
				if (filterPermission.getName() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("name")), Util.wrapSufix(filterPermission.getName().toUpperCase(), searchParam.isExact())));
				}  
				if (filterPermission.getDescription() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("description")), Util.wrapSufix(filterPermission.getDescription().toUpperCase(), searchParam.isExact())));
				}  
				if (filterPermission.getOperation() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("operation")), Util.wrapSufix(filterPermission.getOperation().toUpperCase(), searchParam.isExact())));
				}  
				if (filterPermission.getTagReminder() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("tagReminder")), Util.wrapSufix(filterPermission.getTagReminder().toUpperCase(), searchParam.isExact())));
				}  
				if (filterPermission.getItem() != null) {
					predicates.add(criteriaBuilder.equal(root.get("item").get("id"), filterPermission.getItem()));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
