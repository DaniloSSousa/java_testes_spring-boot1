package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.modelo.StatusTopico;
import br.com.alura.forum.modelo.Topico;

public class TopicoDtoDetailed {
	private Long id;
	private String titulo;
	private String mensagem;
	private String curso;
	private String autor;
	private StatusTopico status;
	private List<RespostaDto> respostas;
	private LocalDateTime dataCriacao;

	public TopicoDtoDetailed(Topico t) {
		this.id = t.getId();
		this.titulo = t.getTitulo();
		this.mensagem = t.getMensagem();
		this.curso = t.getCurso().getNome();
		this.autor = t.getAutor().getNome();
		this.status = t.getStatus();
		this.respostas = t.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList());
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

	public String getAutor() {
		return autor;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaDto> getRespostas() {
		return respostas;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
}
