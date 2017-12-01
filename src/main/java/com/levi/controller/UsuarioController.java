package com.levi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.levi.model.Role;
import com.levi.model.Usuario;
import com.levi.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/usuarios")
	public List<Usuario> getAllUsuarios() {
		return usuarioService.getUsuarios();
	}
	
	@RequestMapping("/usuarios/{idUsuario}")
	public Usuario getUsuario(@PathVariable Integer idUsuario) {
		return usuarioService.getUsuario(idUsuario);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/usuarios")
	public void addUsuario(@RequestBody Usuario usuario) {
		usuarioService.addUsuario(usuario);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/usuarios/{idUsuario}")
	public void updateUsuario(@RequestBody Usuario usuario, @PathVariable Integer idUsuario) {
		usuarioService.updateUsuario(usuario);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/usuarios/{idUsuario}")
	public void deleteUsuario(@PathVariable Integer idUsuario) {
		usuarioService.deleteUsuario(idUsuario);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/cadastrar")
	public void cadastrarUsuario(@RequestBody List<Role> roles, @RequestBody Usuario usuario) {
		usuario.setClientRoles(roles);
		usuarioService.addUsuario(usuario);
	}
}
