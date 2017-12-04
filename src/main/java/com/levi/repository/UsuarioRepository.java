package com.levi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.levi.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.email like :email AND u.password like :senha AND u.enabled = 1")
	public Usuario login(@Param("email") String email, @Param("senha") String senha);

	
}
