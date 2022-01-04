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

import br.com.netflics.model.Linguagem;
import br.com.netflics.persistence.LinguagemRepository;
import br.com.netflics.persistence.LinguagemSpecificationHelper;
import br.com.netflics.model.filter.FilterLinguagem;

import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.core.rs.exception.ValidationException;
import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.DateUtil;
import br.com.netflics.core.utils.Util;

@Service
@Transactional
public class LinguagemServiceImp implements LinguagemService {

	public static final Logger LOGGER = LoggerFactory.getLogger(LinguagemServiceImp.class);
	
	@Autowired
	LinguagemRepository linguagemRepository;
	
	public Optional<Linguagem> get(Integer id, Tenant tenant) {
		return linguagemRepository.findOne(LinguagemSpecificationHelper.fromId(id, tenant));
	}

	public Pager<Linguagem> get(SearchParameters<FilterLinguagem> searchParams, Tenant tenant) {
		Pageable pageRequest = searchParams.getPageRequest();

		Page<Linguagem> page = linguagemRepository.findAll(LinguagemSpecificationHelper.filter(searchParams, tenant), pageRequest);

		return new Pager<Linguagem>(page.getContent(), searchParams.getPage(), searchParams.getPageSize(),
		        searchParams.getOrder(), searchParams.getOrderBy(), page.getTotalElements());
	}

	public Boolean delete(Integer id, Tenant tenant) {
		Optional<Linguagem> optional = this.get(id, tenant);
		if (optional.isPresent()) {
			linguagemRepository.delete(optional.get());
		}
		return true;
	}

	public Linguagem save(Linguagem entity) {
		return linguagemRepository.save(entity);
	}

	public Linguagem update(Linguagem entity) {
		return linguagemRepository.save(entity);
	}
}

//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06