/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06 */
package br.com.netflics.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.netflics.core.model.Tenant ;

import br.com.netflics.model.Role;
import br.com.netflics.persistence.RoleRepository;
import br.com.netflics.persistence.RoleSpecificationHelper;
import br.com.netflics.model.filter.FilterRole;

import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.core.rs.exception.ValidationException;
import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.DateUtil;
import br.com.netflics.core.utils.Util;

@Service
@Transactional
public class RoleServiceImp implements RoleService {

	public static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImp.class);
	
	@Autowired
	RoleRepository roleRepository;
	
	public Optional<Role> get(Integer id) {
		return roleRepository.findById(id);
	}

	public Pager<Role> get(SearchParameters<FilterRole> searchParams) {
		Pageable pageRequest = searchParams.getPageRequest();

		Page<Role> page = roleRepository.findAll(RoleSpecificationHelper.filter(searchParams), pageRequest);

		return new Pager<Role>(page.getContent(), searchParams.getPage(), searchParams.getPageSize(),
		        searchParams.getOrder(), searchParams.getOrderBy(), page.getTotalElements());
	}
	
	public Boolean delete(Integer id) {
		Optional<Role> optional = this.get(id);
		if (optional.isPresent()) {
			roleRepository.delete(optional.get());
		}
		return true;
	}

	public Role save(Role entity) {
		return roleRepository.save(entity);
	}

	public Role update(Role entity) {
		return roleRepository.save(entity);
	}
}

//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06