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

import br.com.netflics.model.Avaliacao;
import br.com.netflics.persistence.AvaliacaoRepository;
import br.com.netflics.persistence.AvaliacaoSpecificationHelper;
import br.com.netflics.model.filter.FilterAvaliacao;

import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.core.rs.exception.ValidationException;
import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.DateUtil;
import br.com.netflics.core.utils.Util;

@Service
@Transactional
public class AvaliacaoServiceImp implements AvaliacaoService {

	public static final Logger LOGGER = LoggerFactory.getLogger(AvaliacaoServiceImp.class);
	
	@Autowired
	AvaliacaoRepository avaliacaoRepository;
	
	public Optional<Avaliacao> get(Integer id, Tenant tenant) {
		return avaliacaoRepository.findOne(AvaliacaoSpecificationHelper.fromId(id, tenant));
	}

	public Pager<Avaliacao> get(SearchParameters<FilterAvaliacao> searchParams, Tenant tenant) {
		Pageable pageRequest = searchParams.getPageRequest();

		Page<Avaliacao> page = avaliacaoRepository.findAll(AvaliacaoSpecificationHelper.filter(searchParams, tenant), pageRequest);

		return new Pager<Avaliacao>(page.getContent(), searchParams.getPage(), searchParams.getPageSize(),
		        searchParams.getOrder(), searchParams.getOrderBy(), page.getTotalElements());
	}

	public Boolean delete(Integer id, Tenant tenant) {
		Optional<Avaliacao> optional = this.get(id, tenant);
		if (optional.isPresent()) {
			avaliacaoRepository.delete(optional.get());
		}
		return true;
	}

	public Avaliacao save(Avaliacao entity) {
		return avaliacaoRepository.save(entity);
	}

	public Avaliacao update(Avaliacao entity) {
		return avaliacaoRepository.save(entity);
	}
}

//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06