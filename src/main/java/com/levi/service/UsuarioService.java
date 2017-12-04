package com.levi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levi.model.Usuario;
import com.levi.repository.UsuarioRepository;

@Service
public class UsuarioService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> getUsuarios(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarioRepository.findAll().forEach(usuarios::add);
		return usuarios;
	}
	
	public Usuario getUsuario(Integer idUsuario) {
		return usuarioRepository.findOne(idUsuario);
	}
	
	public void addUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public void updateUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public void deleteUsuario(Integer idUsuario) {
		usuarioRepository.delete(idUsuario);
	}
	
	public Usuario login(String email, String senha){
		return usuarioRepository.login(email, senha);
	}
}
