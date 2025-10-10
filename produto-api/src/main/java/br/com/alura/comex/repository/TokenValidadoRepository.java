package br.com.alura.comex.repository;

import br.com.alura.comex.model.TokenValidado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenValidadoRepository extends JpaRepository<TokenValidado, Long> {
}
