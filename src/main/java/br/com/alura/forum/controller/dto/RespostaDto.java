package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.modelo.Resposta;

public class RespostaDto {
	private Long id;
	private String mensagem;
	private String autor;
	private LocalDateTime dataCriacao;
	
	public RespostaDto(Resposta r) {
		this.id = r.getId();
		this.mensagem = r.getMensagem();
		this.autor = r.getAutor().getNome();
		this.dataCriacao = r.getDataCriacao();
	}

	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getAutor() {
		return autor;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
}
