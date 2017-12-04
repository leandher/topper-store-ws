package com.levi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.levi.model.Endereco;
import com.levi.service.EnderecoService;

@RestController
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@RequestMapping("/api/api/enderecos")
	public List<Endereco> getAllEnderecos() {
		return enderecoService.getEnderecos();
	}
	
	@RequestMapping("/api/enderecos/{idEndereco}")
	public Endereco getEndereco(@PathVariable Integer idEndereco) {
		return enderecoService.getEndereco(idEndereco);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/enderecos")
	public void addEndereco(@RequestBody Endereco endereco) {
		enderecoService.addEndereco(endereco);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/api/enderecos/{idEndereco}")
	public void updateEndereco(@RequestBody Endereco endereco, @PathVariable Integer idEndereco) {
		enderecoService.updateEndereco(endereco);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/api/enderecos/{idEndereco}")
	public void deleteEndereco(@PathVariable Integer idEndereco) {
		enderecoService.deleteEndereco(idEndereco);
	}
}