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

import br.com.netflics.model.Visualizacao;
import br.com.netflics.persistence.VisualizacaoRepository;
import br.com.netflics.persistence.VisualizacaoSpecificationHelper;
import br.com.netflics.model.filter.FilterVisualizacao;

import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.core.rs.exception.ValidationException;
import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.utils.DateUtil;
import br.com.netflics.core.utils.Util;

@Service
@Transactional
public class VisualizacaoServiceImp implements VisualizacaoService {

	public static final Logger LOGGER = LoggerFactory.getLogger(VisualizacaoServiceImp.class);
	
	@Autowired
	VisualizacaoRepository visualizacaoRepository;
	
	public Optional<Visualizacao> get(Integer id, Tenant tenant) {
		return visualizacaoRepository.findOne(VisualizacaoSpecificationHelper.fromId(id, tenant));
	}

	public Pager<Visualizacao> get(SearchParameters<FilterVisualizacao> searchParams, Tenant tenant) {
		Pageable pageRequest = searchParams.getPageRequest();

		Page<Visualizacao> page = visualizacaoRepository.findAll(VisualizacaoSpecificationHelper.filter(searchParams, tenant), pageRequest);

		return new Pager<Visualizacao>(page.getContent(), searchParams.getPage(), searchParams.getPageSize(),
		        searchParams.getOrder(), searchParams.getOrderBy(), page.getTotalElements());
	}

	public Boolean delete(Integer id, Tenant tenant) {
		Optional<Visualizacao> optional = this.get(id, tenant);
		if (optional.isPresent()) {
			visualizacaoRepository.delete(optional.get());
		}
		return true;
	}

	public Visualizacao save(Visualizacao entity) {
		return visualizacaoRepository.save(entity);
	}

	public Visualizacao update(Visualizacao entity) {
		return visualizacaoRepository.save(entity);
	}
}

//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06