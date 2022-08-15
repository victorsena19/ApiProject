package com.api_project.Service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_project.Entity.Endereco;
import com.api_project.Resource.EnderecoResource;

@Service
public class EnderecoService {
	private static final Logger logger = LogManager.getLogger(EnderecoResource.class);
	
	public Endereco buscaCep(String cep) throws Exception {
		
		if (cep.toString().length() != 8) {
			throw new RuntimeException("CEP DEVE CONTER 8 DIGITOS");
		}
		String link = "https://viacep.com.br/ws/";
		String urlChamada = link + cep + "/json/";
		
		logger.info("URL======"+urlChamada);
		
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<Endereco> responseCep = restTemplate.exchange(urlChamada, HttpMethod.GET, null, Endereco.class);
			if(responseCep.getStatusCodeValue() != 200) {
				throw new RuntimeException("Http error code: " + responseCep.getStatusCodeValue());
			}
			
			Endereco listaEndereco = responseCep.getBody();
			return listaEndereco;
			
		} catch (Exception e) {
			throw new Exception("Erro:" + e);
		}
	}
	
	public List<Endereco> busca(String uf, String localidade, String logradouro) throws Exception{
		String link = "https://viacep.com.br/ws/";
		String urlChamada = link + uf + "/" + localidade + "/"+ logradouro + "/json/";
		RestTemplate rest = new RestTemplate();
		
		try {
			ResponseEntity<List<Endereco>> responseBusc = rest.exchange(urlChamada, HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Endereco>>() {} );
			if(responseBusc.getStatusCodeValue() != 200) {
				throw new RuntimeException("Http error code: " + responseBusc.getStatusCodeValue());
			}
			logger.info("Json======"+responseBusc.getBody());
			
			List<Endereco> endereco = responseBusc.getBody();
			return endereco;
		}catch(Exception e){
			throw new Exception("Erro:" + e);
		}
	}
}