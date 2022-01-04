package br.com.netflics.model.filter;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;

/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06 */
public class FilterFilme implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tituloOriginal;  			
	private String poster;  			
	private Integer duracao;  			
	private LocalDate dataLancamento;  			
	private String sinopse;  			
	private String titulo;  			
	private String diretor;  			

	private Integer classificacao;		
	private Integer linguagem;		
	
	public  FilterFilme() {
		
	}

	public String getTituloOriginal() {
		return tituloOriginal;
	}

	public void setTituloOriginal(String tituloOriginal) {
		this.tituloOriginal = tituloOriginal;
	}
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
		
	public Integer getClassificacao() {
		return classificacao;
	}
	
	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}
	public Integer getLinguagem() {
		return linguagem;
	}
	
	public void setLinguagem(Integer linguagem) {
		this.linguagem = linguagem;
	}
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06