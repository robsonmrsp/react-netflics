package br.com.netflics.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;


/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06 */
public class JsonAvaliacao implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LocalDateTime dataHora;
	private JsonFilme filme;		
	private JsonCritico critico;		
	
	public  JsonAvaliacao() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}				
	
	public JsonFilme getFilme() {
		return filme;
	}
	
	public void setFilme(JsonFilme filme) {
		this.filme = filme;
	}
	public JsonCritico getCritico() {
		return critico;
	}
	
	public void setCritico(JsonCritico critico) {
		this.critico = critico;
	}

}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06