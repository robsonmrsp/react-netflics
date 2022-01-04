/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06 */
package br.com.netflics.service;
import java.util.Optional;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import br.com.netflics.core.model.Tenant;
import br.com.netflics.model.Permission;
import br.com.netflics.model.filter.FilterPermission;

import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.core.persistence.pagination.SearchParameters;

public interface PermissionService {

	
	public Optional<Permission> get(Integer id) ;

	public Pager<Permission> get(SearchParameters<FilterPermission> searchParams) ;
	
	public Boolean delete(Integer id);

	public Permission save(Permission entity) ;

	public Permission update(Permission entity) ;

}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06