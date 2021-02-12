package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.modelo.Topico;

public class TopicoDto {
	private Long id;
	private String titulo;
	private String mensagem;
	private String curso;
	private LocalDateTime dataCriacao;

	public TopicoDto(Topico t) {
		this.id = t.getId();
		this.titulo = t.getTitulo();
		this.mensagem = t.getMensagem();
		this.curso = t.getCurso().getNome();
		this.dataCriacao = t.getDataCriacao();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getCurso() {
		return curso;
	}
	
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public static List<TopicoDto> forListDto(List<Topico> l) {
		return l.stream().map(TopicoDto::new).collect(Collectors.toList());
	}

	public static TopicoDto forTopicoDto(Topico t) {
		return new TopicoDto(t);
	}
}
