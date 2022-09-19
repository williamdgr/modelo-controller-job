package br.com.modelo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.modelo.dto.CepConsultaDTO;
import br.com.modelo.dto.CepConsultaEnvioDTO;
import br.com.modelo.service.CepConsultaService;

@RestController
public class CepController {

    @RequestMapping(value = "/cepConsulta/{cep}", method = RequestMethod.GET)
    public ResponseEntity<CepConsultaDTO> cepConsulta(@PathVariable(value = "cep") String cep) {
    	CepConsultaService service = new CepConsultaService();
        return service.consultarUmCep(cep);
    }
    
    @RequestMapping(value = "/consultarPorEndereco/{uf}/{cidade}/{logradouro}", method = RequestMethod.GET)
    public List<CepConsultaDTO> consultarPorEndereco(
    		@PathVariable(value = "uf") String uf,
    		@PathVariable(value = "cidade") String cidade,
    		@PathVariable(value = "logradouro") String logradouro) {
    	CepConsultaService service = new CepConsultaService();
        return service.consultarPorEndereco(uf, cidade, logradouro);
    }
    
    @RequestMapping(value = "/consultarCep", method = RequestMethod.POST)
    public ResponseEntity<CepConsultaDTO> consultarCep(@Valid @RequestBody CepConsultaEnvioDTO envio) {
    	CepConsultaService service = new CepConsultaService();
        return service.consultarCep(envio);
    }
}

