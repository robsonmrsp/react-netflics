package br.com.netflics.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;


/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06 */
public class JsonFilme implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String tituloOriginal;
	private String poster;
	private Integer duracao;
	private LocalDate dataLancamento;  			
	private String sinopse;
	private String titulo;
	private String diretor;
	private JsonClassificacao classificacao;		
	private ArrayList<JsonGenero> genero = new ArrayList<JsonGenero>();		
	private ArrayList<JsonAtor> elenco = new ArrayList<JsonAtor>();	
	private JsonLinguagem linguagem;		
	private ArrayList<JsonAvaliacao> avaliacoes = new ArrayList<JsonAvaliacao>();		
	
	public  JsonFilme() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public JsonClassificacao getClassificacao() {
		return classificacao;
	}
	
	public void setClassificacao(JsonClassificacao classificacao) {
		this.classificacao = classificacao;
	}
	public ArrayList<JsonGenero> getGenero() {
		return genero;
	}
	
	public void setGenero(ArrayList<JsonGenero> genero) {
		this.genero = genero;
	}

	public ArrayList<JsonAtor> getElenco() {
		return elenco;
	}
	
	public void setElenco(ArrayList<JsonAtor> ator) {
		this.elenco = ator;
	}

	public JsonLinguagem getLinguagem() {
		return linguagem;
	}
	
	public void setLinguagem(JsonLinguagem linguagem) {
		this.linguagem = linguagem;
	}
	public ArrayList<JsonAvaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	
	public void setAvaliacoes(ArrayList<JsonAvaliacao> avaliacao) {
		this.avaliacoes = avaliacao;
	}


}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06