package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService{
	
	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) throws UsernameNotFoundException {
		Object usuario = manager.createQuery("select u from Usuario u where u.email = :email")
		.setParameter("email", email).getSingleResult();
		
		if(usuario == null){
			throw new UsernameNotFoundException("Usuario " + email + "não encontrado!");
		}
		
		return (Usuario) usuario;
	}

}
