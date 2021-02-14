package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	// _ é usado para separar os relacionamentos entre entidades
	List<Topico> findByCurso_Nome(String curso);
}
