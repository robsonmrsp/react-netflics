/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06 */
package br.com.netflics.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
	
import java.util.ArrayList;
import java.util.List;

import br.com.netflics.core.model.AbstractMultitenantEntity;

@Entity
@Table(name = "FILME")
public class Filme extends AbstractMultitenantEntity{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer id;
	
	@Column(name = "TITULO_ORIGINAL" )
	private String tituloOriginal;		
	
	@Column(name = "POSTER" )
	private String poster;		
	
	@Column(name = "DURACAO")
	private Integer duracao;  	
			
	@Column(name = "DATA_LANCAMENTO")
	private LocalDate dataLancamento;  
				
	@Column(name = "SINOPSE" , columnDefinition="varchar" ) 
	private String sinopse;		
	
	@Column(name = "TITULO" )
	private String titulo;		
	
	@Column(name = "DIRETOR" )
	private String diretor;		
	
	@ManyToOne
	@JoinColumn(name = "ID_CLASSIFICACAO")
	private Classificacao classificacao;
			
	@OneToMany()
	private List<Genero> genero;	
		
    @ManyToMany
    @JoinTable(name="FILME_ATOR", joinColumns = @JoinColumn(name = "ID_FILME", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_ATOR", referencedColumnName = "ID") )
    private List<Ator> elenco;
    
	@ManyToOne
	@JoinColumn(name = "ID_LINGUAGEM")
	private Linguagem linguagem;
			
	@OneToMany(mappedBy="filme")
	private List<Avaliacao> avaliacoes;	
		
		
	public  Filme() {
		
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
	public Classificacao getClassificacao() {
		return classificacao;
	}
	
	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}
	
	public void setGenero(List<Genero> genero){
		this.genero = genero;
	}
	
	public List<Genero>  getGenero() {
		if(this.genero == null){
			setGenero(new ArrayList<Genero>());
		}
		return this.genero;
	}
		
	public boolean addGenero(Genero genero){
		return getGenero().add(genero);
	}
	
	public boolean removeGenero(Genero genero){
		return getGenero().remove(genero);
	}
	
	public void setElenco(List<Ator> elenco){
		this.elenco = elenco;
	}
	
	public List<Ator>  getElenco() {
		if(this.elenco == null){
			setElenco(new ArrayList<Ator>());
		}
		return this.elenco; 
	}
		
	public boolean addElenco(Ator ator){	
		return getElenco().add(ator);
	}
	
	public boolean removeElenco(Ator ator){	
		return getElenco().remove(ator);
	}
	public Linguagem getLinguagem() {
		return linguagem;
	}
	
	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}
	
	public void setAvaliacoes(List<Avaliacao> avaliacoes){
		this.avaliacoes = avaliacoes;
	}
	
	public List<Avaliacao>  getAvaliacoes() {
		if(this.avaliacoes == null){
			setAvaliacoes(new ArrayList<Avaliacao>());
		}
		return this.avaliacoes;
	}
		
	public boolean addAvaliacoes(Avaliacao avaliacao){
		avaliacao.setFilme(this);
		return getAvaliacoes().add(avaliacao);
	}
	
	public boolean removeAvaliacoes(Avaliacao avaliacao){
		avaliacao.setFilme(null);
		return getAvaliacoes().remove(avaliacao);
	}
	
	
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06