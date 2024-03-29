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
import javax.persistence.UniqueConstraint;		

import java.time.LocalDate;
import java.time.LocalDateTime;
	
import java.util.ArrayList;
import java.util.List;

import br.com.netflics.core.model.AbstractMultitenantEntity;

@Entity
@Table(name = "ATOR", uniqueConstraints = {
		@UniqueConstraint(name = "ATOR_NOME", columnNames = { "NOME",  "ID_TENANT"}), 
})
public class Ator extends AbstractMultitenantEntity{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer id;
	
	@Column(name = "BIOGRAFIA" , columnDefinition="varchar" ) 
	private String biografia;		
	
	@Column(name = "DATA_NASCIMENTO")
	private LocalDate dataNascimento;  
				
	@Column(name = "NOME" )
	private String nome;		
	
	@Column(name = "FOTO" )
	private String foto;		
	
    @ManyToMany(mappedBy="elenco")
    private List<Filme> filmes;
		
	public  Ator() {
		
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	public void setFilmes(List<Filme> filmes){
		this.filmes = filmes;
	}
	
	public List<Filme>  getFilmes() {
		if(this.filmes == null){
			setFilmes(new ArrayList<Filme>());
		}
		return this.filmes; 
	}
		
	public boolean addFilmes(Filme filme){	
		return getFilmes().add(filme);
	}
	
	public boolean removeFilmes(Filme filme){	
		return getFilmes().remove(filme);
	}
	
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06