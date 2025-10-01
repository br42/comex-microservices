package br.com.alura.comex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    
    default Usuario findByLogin(String username) {
        return findByEmail(username);
    };
}