package com.api_project.Resource;

import java.net.http.HttpHeaders;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api_project.Entity.Endereco;
import com.api_project.Entity.Response;
import com.api_project.Service.EnderecoService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/cep")
public class EnderecoResource {

	private static final Logger logger = LogManager.getLogger(EnderecoResource.class);

	@Autowired
	EnderecoService service;
	
	Endereco endereco;
	
	/*
	@GetMapping
	public ResponseEntity<Endereco> buscaCep(@RequestParam(value = "cep") String cep) throws Exception {
		logger.info("CEP======" + cep.toString());
		Endereco busca = service.buscaCep(cep);
		return ResponseEntity.ok().body(busca);
		}
*/
		@RequestMapping(method = RequestMethod.GET)
		public Response<Object> buscaAll(
				@RequestParam(value = "uf", required = false) String uf,
				@RequestParam(value = "localidade", required = false) String localidade,
				@RequestParam(value = "logradouro", required = false) String logradouro,
				@RequestParam(value = "cep", required = false)String cep) throws Exception {
			
			Response<Object> response = new Response<Object>();
			//JsonObject json = new JsonObject();
			if(cep != null) {
			
				Endereco busca = service.buscaCep(cep);
				response.setBody(busca);
				response.setStatus(HttpStatus.OK);
				return response;
				
			}else  if (uf != null && localidade != null && logradouro != null){
				List<Endereco> busca = service.busca(uf, localidade, logradouro);
				response.setBody(busca);
				response.setStatus(HttpStatus.OK);
				return response;
			}
			else {
				throw new RuntimeException("Erro nos parametros");
			}
			
		}
}
