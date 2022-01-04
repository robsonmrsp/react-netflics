/*  generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06 */
package br.com.netflics.rs;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import br.com.netflics.core.json.JsonError;
import br.com.netflics.core.json.JsonPaginator;
import br.com.netflics.json.JsonPermission;

import br.com.netflics.model.Permission;

import br.com.netflics.service.PermissionService;
import br.com.netflics.model.filter.FilterPermission;
import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.rs.exception.ValidationException;
import br.com.netflics.core.security.SpringSecurityUserContext;

import br.com.netflics.utils.Parser;
@RestController
@RequestMapping("/rs/crud/permissions")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PermissionController {
	@Autowired
	PermissionService permissionService;
	public static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@RequestParam MultiValueMap<String, String> mapParams) {
		ResponseEntity response = null;

		Pager<Permission> permissions = null;
		try {
			SearchParameters<FilterPermission> paginationParams = new SearchParameters<FilterPermission>(mapParams, FilterPermission.class);

			permissions = permissionService.get(paginationParams);
			
			JsonPaginator<JsonPermission> paginator = JsonPaginator.of(Parser.toListJsonPermissions(permissions.getItems()),
				 permissions.getActualPage(), permissions.getPageSize(), permissions.getOrder(), permissions.getOrderBy(),permissions.getTotalRecords());

			response = ResponseEntity.ok(paginator);

		} catch (Exception e) {
			String message = String.format("Não foi possivel carregar permissions para os parametros %s [%s]", mapParams.toString(), e.getMessage());
			LOGGER.error(message, e);
			response = ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, null));
		}
		return response;
	}
	
	@RequestMapping(value = "{id:\\d+}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@PathVariable("id") int id) {
		try {
			Optional<Permission> optional = permissionService.get(id);
			
			if (optional.isPresent()) {
				return ResponseEntity.ok(Parser.toJson(optional.get()));
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			String message = String.format("Não foi possivel carregar o registro. [ %s ] parametros [ %d ]", e.getMessage(), id);
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, null));
		}
	}

	@RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity save(@RequestBody JsonPermission jsonPermission) {
		try {
			Permission permission = Parser.toEntity(jsonPermission);

			permission = permissionService.save(permission);

			return ResponseEntity.status(CREATED).body(Parser.toJson(permission));

		} catch (ValidationException e) {
			String message = String.format("Não foi possivel salvar  o registro [ %s ] parametros [ %s ]", e.getOrigem().getMessage(), jsonPermission.toString());
			LOGGER.error(message, e.getOrigem());
			return ResponseEntity.status(BAD_REQUEST).body(new JsonError(e, message, jsonPermission, e.getLegalMessage()));
		} catch (Exception e) {
			String message = String.format("Não foi possivel salvar  permission [ %s ] parametros [ %s ]", e.getMessage(), jsonPermission.toString());
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, jsonPermission));

		}
	}

	@RequestMapping(value = "{id:\\d+}", method = PUT, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody JsonPermission jsonPermission) {
		try {
			Permission permission = Parser.toEntity(jsonPermission);

			permission = permissionService.update(permission);

			return ResponseEntity.ok(Parser.toJson(permission));
		} catch (ValidationException e) {
			String message = String.format("Não foi possivel salvar  o registro [ %s ] parametros [ %s ]", e.getOrigem().getMessage(), jsonPermission.toString());
			LOGGER.error(message, e.getOrigem());
			return ResponseEntity.status(BAD_REQUEST).body(new JsonError(e, message, jsonPermission, e.getLegalMessage()));
		} catch (Exception e) {
			String message = String.format("Não foi possivel salvar o registro [ %s ] parametros [ %s ]", e.getMessage(), jsonPermission.toString());
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, jsonPermission));
		}
	}
	
	@RequestMapping(value = "{id:\\d+}", method = DELETE)
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		try {
			permissionService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			String message = String.format("Não foi possivel remover o registro [ %s ] parametros [ %s ]", e.getMessage(), id);
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, id));
		}
	}

}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06
