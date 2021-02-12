package br.com.alura.forum.config.validacao.dto;

public class FormErrorDto {
	private String campo;
	private String mensagem;
	
	public FormErrorDto(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}
}
