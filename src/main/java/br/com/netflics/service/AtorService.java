/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06 */
package br.com.netflics.service;
import java.util.Optional;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import br.com.netflics.core.model.Tenant;
import br.com.netflics.model.Ator;
import br.com.netflics.model.filter.FilterAtor;

import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.core.persistence.pagination.SearchParameters;

public interface AtorService {

	
	public Optional<Ator> get(Integer id, Tenant tenant) ;

	public Pager<Ator> get(SearchParameters<FilterAtor> searchParams, Tenant tenant) ;
	
	public Boolean delete(Integer id, Tenant tenant);

	public Ator save(Ator entity) ;

	public Ator update(Ator entity) ;

}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06