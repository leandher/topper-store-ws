package com.levi.controller;

import java.util.List;

import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.levi.auxiliar.Role;
import com.levi.model.Usuario;
import com.levi.service.UsuarioService;

@Transactional
@CrossOrigin("*")
@RequestMapping("/api/usuarios")
@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getLogin(@RequestBody Usuario user) {
		Usuario usuario = usuarioService.login(user.getEmail(),
				user.getPassword());

		if (usuario != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Usuario> getAllUsuarios() {
		return usuarioService.getUsuarios();
	}

	@RequestMapping(value = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario getUsuario(@PathVariable Integer idUsuario) {
		return usuarioService.getUsuario(idUsuario);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
		usuario.setUserRole(Role.USER.toString());
		usuario.setEnabled(true);
		try {
			usuarioService.addUsuario(usuario);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}  catch(Exception e){
			return new ResponseEntity<Usuario>(new Usuario(), HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario,
			@PathVariable Integer idUsuario) {

		try {
			usuarioService.updateUsuario(usuario);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} catch (RollbackException e) {
			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{idUsuario}")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable Integer idUsuario) {
		usuarioService.deleteUsuario(idUsuario);
		return new ResponseEntity<Usuario>(HttpStatus.OK);
	}
}
