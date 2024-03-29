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
@Table(name = "GENERO", uniqueConstraints = {
		@UniqueConstraint(name = "GENERO_NOME", columnNames = { "NOME",  "ID_TENANT"}), 
		@UniqueConstraint(name = "GENERO_DESCRICAO", columnNames = { "DESCRICAO",  "ID_TENANT"}), 
})
public class Genero extends AbstractMultitenantEntity{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer id;
	
	@Column(name = "NOME" )
	private String nome;		
	
	@Column(name = "DESCRICAO" )
	private String descricao;		
	
		
	public  Genero() {
		
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06