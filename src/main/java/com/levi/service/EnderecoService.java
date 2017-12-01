package com.levi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levi.model.Endereco;
import com.levi.model.Usuario;
import com.levi.repository.EnderecoRepository;
import com.levi.repository.UsuarioRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Endereco> getEnderecos(){
		List<Endereco> enderecos = new ArrayList<Endereco>();
		enderecoRepository.findAll().forEach(enderecos::add);
		return enderecos;
	}
	
	public Endereco getEndereco(Integer idEndereco) {
		return enderecoRepository.findOne(idEndereco);
	}
	
	public void addEndereco(Endereco endereco) {
		enderecoRepository.save(endereco);
	}

	public void updateEndereco(Endereco endereco) {
		enderecoRepository.save(endereco);
	}

	public void deleteEndereco(Integer idEndereco) {
		enderecoRepository.delete(idEndereco);
	}
	
	public Endereco getEnderecoDoUsuario(Integer idUsuario) {
		return usuarioRepository.findOne(idUsuario).getEndereco();
	}
	
	public void addEnderecoDoUsuario(Endereco endereco, Integer idUsuario) {
		Usuario usuario = usuarioRepository.findOne(idUsuario);
		usuario.setEndereco(endereco);
		usuarioRepository.save(usuario);
	}
	
	public void deleteEnderecoDoUsuario(Endereco endereco, Integer idUsuario) {
		Usuario usuario = usuarioRepository.findOne(idUsuario);
		usuario.setEndereco(null);
		usuarioRepository.save(usuario);
	}
	
}
