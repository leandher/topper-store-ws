package com.levi.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.levi.auxiliar.Role;
import com.levi.model.Usuario;
import com.levi.service.UsuarioService;

@Transactional
@CrossOrigin("*")
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method=RequestMethod.POST, value="/api/login")
	public ResponseEntity<Usuario> getLogin(@RequestBody Usuario user) {
		Usuario usuario = usuarioService.login(user.getEmail(), user.getPassword());
		
		if(usuario != null){
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/api/usuarios")
	public List<Usuario> getAllUsuarios() {
		return usuarioService.getUsuarios();
	}
	
	@RequestMapping("/api/usuarios/{idUsuario}")
	public Usuario getUsuario(@PathVariable Integer idUsuario) {
		return usuarioService.getUsuario(idUsuario);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/usuarios")
	public void addUsuario(@RequestBody Usuario usuario) {
		usuarioService.addUsuario(usuario);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/api/usuarios/{idUsuario}")
	public void updateUsuario(@RequestBody Usuario usuario, @PathVariable Integer idUsuario) {
		usuario.setUserRole(Role.USER.toString());
		usuarioService.updateUsuario(usuario);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/api/usuarios/{idUsuario}")
	public void deleteUsuario(@PathVariable Integer idUsuario) {
		usuarioService.deleteUsuario(idUsuario);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/api/cadastrar")
	public void cadastrarUsuario(@RequestBody Usuario usuario) {
		usuario.setUserRole(Role.USER.toString());
		usuarioService.addUsuario(usuario);
	}
}
