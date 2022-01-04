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
import br.com.netflics.json.JsonGroup;

import br.com.netflics.model.Group;

import br.com.netflics.service.GroupService;
import br.com.netflics.model.filter.FilterGroup;
import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.rs.exception.ValidationException;
import br.com.netflics.core.security.SpringSecurityUserContext;

import br.com.netflics.utils.Parser;
@RestController
@RequestMapping("/rs/crud/groups")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GroupController {
	@Autowired
	GroupService groupService;
	public static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@RequestParam MultiValueMap<String, String> mapParams) {
		ResponseEntity response = null;

		Pager<Group> groups = null;
		try {
			SearchParameters<FilterGroup> paginationParams = new SearchParameters<FilterGroup>(mapParams, FilterGroup.class);

			groups = groupService.get(paginationParams);
			
			JsonPaginator<JsonGroup> paginator = JsonPaginator.of(Parser.toListJsonGroups(groups.getItems()),
				 groups.getActualPage(), groups.getPageSize(), groups.getOrder(), groups.getOrderBy(),groups.getTotalRecords());

			response = ResponseEntity.ok(paginator);

		} catch (Exception e) {
			String message = String.format("Não foi possivel carregar groups para os parametros %s [%s]", mapParams.toString(), e.getMessage());
			LOGGER.error(message, e);
			response = ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, null));
		}
		return response;
	}
	
	@RequestMapping(value = "{id:\\d+}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@PathVariable("id") int id) {
		try {
			Optional<Group> optional = groupService.get(id);
			
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
	public ResponseEntity save(@RequestBody JsonGroup jsonGroup) {
		try {
			Group group = Parser.toEntity(jsonGroup);

			group = groupService.save(group);

			return ResponseEntity.status(CREATED).body(Parser.toJson(group));

		} catch (ValidationException e) {
			String message = String.format("Não foi possivel salvar  o registro [ %s ] parametros [ %s ]", e.getOrigem().getMessage(), jsonGroup.toString());
			LOGGER.error(message, e.getOrigem());
			return ResponseEntity.status(BAD_REQUEST).body(new JsonError(e, message, jsonGroup, e.getLegalMessage()));
		} catch (Exception e) {
			String message = String.format("Não foi possivel salvar  group [ %s ] parametros [ %s ]", e.getMessage(), jsonGroup.toString());
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, jsonGroup));

		}
	}

	@RequestMapping(value = "{id:\\d+}", method = PUT, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody JsonGroup jsonGroup) {
		try {
			Group group = Parser.toEntity(jsonGroup);

			group = groupService.update(group);

			return ResponseEntity.ok(Parser.toJson(group));
		} catch (ValidationException e) {
			String message = String.format("Não foi possivel salvar  o registro [ %s ] parametros [ %s ]", e.getOrigem().getMessage(), jsonGroup.toString());
			LOGGER.error(message, e.getOrigem());
			return ResponseEntity.status(BAD_REQUEST).body(new JsonError(e, message, jsonGroup, e.getLegalMessage()));
		} catch (Exception e) {
			String message = String.format("Não foi possivel salvar o registro [ %s ] parametros [ %s ]", e.getMessage(), jsonGroup.toString());
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, jsonGroup));
		}
	}
	
	@RequestMapping(value = "{id:\\d+}", method = DELETE)
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		try {
			groupService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			String message = String.format("Não foi possivel remover o registro [ %s ] parametros [ %s ]", e.getMessage(), id);
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, id));
		}
	}

}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06
