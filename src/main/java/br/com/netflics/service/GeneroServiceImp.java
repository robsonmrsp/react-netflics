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

import br.com.netflics.model.Genero;
import br.com.netflics.persistence.GeneroRepository;
import br.com.netflics.persistence.GeneroSpecificationHelper;
import br.com.netflics.model.filter.FilterGenero;

import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.core.rs.exception.ValidationException;
import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.DateUtil;
import br.com.netflics.core.utils.Util;

@Service
@Transactional
public class GeneroServiceImp implements GeneroService {

	public static final Logger LOGGER = LoggerFactory.getLogger(GeneroServiceImp.class);
	
	@Autowired
	GeneroRepository generoRepository;
	
	public Optional<Genero> get(Integer id, Tenant tenant) {
		return generoRepository.findOne(GeneroSpecificationHelper.fromId(id, tenant));
	}

	public Pager<Genero> get(SearchParameters<FilterGenero> searchParams, Tenant tenant) {
		Pageable pageRequest = searchParams.getPageRequest();

		Page<Genero> page = generoRepository.findAll(GeneroSpecificationHelper.filter(searchParams, tenant), pageRequest);

		return new Pager<Genero>(page.getContent(), searchParams.getPage(), searchParams.getPageSize(),
		        searchParams.getOrder(), searchParams.getOrderBy(), page.getTotalElements());
	}

	public Boolean delete(Integer id, Tenant tenant) {
		Optional<Genero> optional = this.get(id, tenant);
		if (optional.isPresent()) {
			generoRepository.delete(optional.get());
		}
		return true;
	}

	public Genero save(Genero entity) {
		return generoRepository.save(entity);
	}

	public Genero update(Genero entity) {
		return generoRepository.save(entity);
	}
}

//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06