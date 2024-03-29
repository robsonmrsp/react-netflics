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
import br.com.netflics.json.JsonAvaliacao;

import br.com.netflics.model.Avaliacao;

import br.com.netflics.service.AvaliacaoService;
import br.com.netflics.model.filter.FilterAvaliacao;
import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.rs.exception.ValidationException;
import br.com.netflics.core.security.SpringSecurityUserContext;

import br.com.netflics.utils.Parser;
@RestController
@RequestMapping("/rs/crud/avaliacaos")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AvaliacaoController {
	@Autowired
	AvaliacaoService avaliacaoService;
	@Autowired
	private SpringSecurityUserContext context;
	public static final Logger LOGGER = LoggerFactory.getLogger(AvaliacaoController.class);

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@RequestParam MultiValueMap<String, String> mapParams) {
		ResponseEntity response = null;

		Pager<Avaliacao> avaliacaos = null;
		try {
			SearchParameters<FilterAvaliacao> paginationParams = new SearchParameters<FilterAvaliacao>(mapParams, FilterAvaliacao.class);

			avaliacaos = avaliacaoService.get(paginationParams, context.getTenant());
			
			JsonPaginator<JsonAvaliacao> paginator = JsonPaginator.of(Parser.toListJsonAvaliacaos(avaliacaos.getItems()),
				 avaliacaos.getActualPage(), avaliacaos.getPageSize(), avaliacaos.getOrder(), avaliacaos.getOrderBy(),avaliacaos.getTotalRecords());

			response = ResponseEntity.ok(paginator);

		} catch (Exception e) {
			String message = String.format("Não foi possivel carregar avaliacaos para os parametros %s [%s]", mapParams.toString(), e.getMessage());
			LOGGER.error(message, e);
			response = ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, null));
		}
		return response;
	}
	
	@RequestMapping(value = "{id:\\d+}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@PathVariable("id") int id) {
		try {
			Optional<Avaliacao> optional = avaliacaoService.get(id, context.getTenant());
			
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
	public ResponseEntity save(@RequestBody JsonAvaliacao jsonAvaliacao) {
		try {
			Avaliacao avaliacao = Parser.toEntity(jsonAvaliacao);

			avaliacao = avaliacaoService.save(avaliacao);

			return ResponseEntity.status(CREATED).body(Parser.toJson(avaliacao));

		} catch (ValidationException e) {
			String message = String.format("Não foi possivel salvar  o registro [ %s ] parametros [ %s ]", e.getOrigem().getMessage(), jsonAvaliacao.toString());
			LOGGER.error(message, e.getOrigem());
			return ResponseEntity.status(BAD_REQUEST).body(new JsonError(e, message, jsonAvaliacao, e.getLegalMessage()));
		} catch (Exception e) {
			String message = String.format("Não foi possivel salvar  avaliacao [ %s ] parametros [ %s ]", e.getMessage(), jsonAvaliacao.toString());
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, jsonAvaliacao));

		}
	}

	@RequestMapping(value = "{id:\\d+}", method = PUT, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody JsonAvaliacao jsonAvaliacao) {
		try {
			Avaliacao avaliacao = Parser.toEntity(jsonAvaliacao);

			avaliacao = avaliacaoService.update(avaliacao);

			return ResponseEntity.ok(Parser.toJson(avaliacao));
		} catch (ValidationException e) {
			String message = String.format("Não foi possivel salvar  o registro [ %s ] parametros [ %s ]", e.getOrigem().getMessage(), jsonAvaliacao.toString());
			LOGGER.error(message, e.getOrigem());
			return ResponseEntity.status(BAD_REQUEST).body(new JsonError(e, message, jsonAvaliacao, e.getLegalMessage()));
		} catch (Exception e) {
			String message = String.format("Não foi possivel salvar o registro [ %s ] parametros [ %s ]", e.getMessage(), jsonAvaliacao.toString());
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, jsonAvaliacao));
		}
	}
	
	@RequestMapping(value = "{id:\\d+}", method = DELETE)
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		try {
			avaliacaoService.delete(id, context.getTenant());
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			String message = String.format("Não foi possivel remover o registro [ %s ] parametros [ %s ]", e.getMessage(), id);
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, id));
		}
	}

}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06
