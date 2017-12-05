package com.levi.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.levi.model.Endereco;
import com.levi.service.EnderecoService;

@Transactional
@CrossOrigin("*")
@RequestMapping("/api/enderecos")
@Controller
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Endereco> getAllEnderecos() {
		return enderecoService.getEnderecos();
	}

	@RequestMapping(value = "/{idEndereco}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Endereco getEndereco(@PathVariable Integer idEndereco) {
		return enderecoService.getEndereco(idEndereco);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Endereco> addEndereco(@RequestBody Endereco endereco) {
		try {
			enderecoService.addEndereco(endereco);
			return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<Endereco>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{idEndereco}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Endereco> updateEndereco(
			@RequestBody Endereco endereco, @PathVariable Integer idEndereco) {

		try {
			enderecoService.updateEndereco(endereco);
			return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<Endereco>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{idEndereco}")
	public ResponseEntity<Endereco> deleteEndereco(
			@PathVariable Integer idEndereco) {
		enderecoService.deleteEndereco(idEndereco);
		return new ResponseEntity<Endereco>(HttpStatus.OK);
	}
}