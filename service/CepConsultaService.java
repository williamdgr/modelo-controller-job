package br.com.modelo.service;

import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import br.com.modelo.dto.CepConsultaDTO;
import br.com.modelo.dto.CepConsultaEnvioDTO;

public class CepConsultaService {
	
	public ResponseEntity<CepConsultaDTO> consultarUmCep(String cep) {
		try {
			//final RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory()); //Sem PROXY
			final RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory()); //Com PROXY
			String url = "https://viacep.com.br/ws/" + cep + "/json/";
			ResponseEntity<CepConsultaDTO> response = restTemplate.getForEntity(url, CepConsultaDTO.class);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<CepConsultaDTO> consultarPorEndereco(String uf, String cidade, String logradouro) {
		try {
			//final RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory()); //Sem PROXY
			final RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory()); //Com PROXY
			String url = "https://viacep.com.br/ws/" + uf + "/" + cidade + "/" + logradouro + "/json/";
			ResponseEntity<List<CepConsultaDTO>> rateResponse =
			        restTemplate.exchange(url,HttpMethod.GET, null, new ParameterizedTypeReference<List<CepConsultaDTO>>() {
			            });
			
			return rateResponse.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResponseEntity<CepConsultaDTO> consultarCep(CepConsultaEnvioDTO envio) {
		try {
			//final RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory()); //Sem PROXY
			final RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory()); //Com PROXY
			String url = "https://viacep.com.br/ws/" + envio.getCep() + "/json/";
			ResponseEntity<CepConsultaDTO> response = restTemplate.getForEntity(url, CepConsultaDTO.class);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		
		 	final String username = "gomes_cb";
	        final String password = "private3";
	        final String proxyUrl = "proxy.muffato.com.br";
	        final int port = 3128;

	        CredentialsProvider credsProvider = new BasicCredentialsProvider();
	        credsProvider.setCredentials( 
	                new AuthScope(proxyUrl, port), 
	                new UsernamePasswordCredentials(username, password));

	        HttpHost myProxy = new HttpHost(proxyUrl, port);
	        HttpClientBuilder clientBuilder = HttpClientBuilder.create();

	        clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();

	        HttpClient httpClient = clientBuilder.build();
	        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	        factory.setHttpClient(httpClient);
	        return factory;

    }
}
