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

import br.com.netflics.model.Assinante;
import br.com.netflics.persistence.AssinanteRepository;
import br.com.netflics.persistence.AssinanteSpecificationHelper;
import br.com.netflics.model.filter.FilterAssinante;

import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.core.rs.exception.ValidationException;
import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.DateUtil;
import br.com.netflics.core.utils.Util;

@Service
@Transactional
public class AssinanteServiceImp implements AssinanteService {

	public static final Logger LOGGER = LoggerFactory.getLogger(AssinanteServiceImp.class);
	
	@Autowired
	AssinanteRepository assinanteRepository;
	
	public Optional<Assinante> get(Integer id, Tenant tenant) {
		return assinanteRepository.findOne(AssinanteSpecificationHelper.fromId(id, tenant));
	}

	public Pager<Assinante> get(SearchParameters<FilterAssinante> searchParams, Tenant tenant) {
		Pageable pageRequest = searchParams.getPageRequest();

		Page<Assinante> page = assinanteRepository.findAll(AssinanteSpecificationHelper.filter(searchParams, tenant), pageRequest);

		return new Pager<Assinante>(page.getContent(), searchParams.getPage(), searchParams.getPageSize(),
		        searchParams.getOrder(), searchParams.getOrderBy(), page.getTotalElements());
	}

	public Boolean delete(Integer id, Tenant tenant) {
		Optional<Assinante> optional = this.get(id, tenant);
		if (optional.isPresent()) {
			assinanteRepository.delete(optional.get());
		}
		return true;
	}

	public Assinante save(Assinante entity) {
		return assinanteRepository.save(entity);
	}

	public Assinante update(Assinante entity) {
		return assinanteRepository.save(entity);
	}
}

//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06