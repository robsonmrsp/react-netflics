package br.com.netflics.model.filter;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;

/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06 */
public class FilterAvaliacao implements Serializable {
	private static final long serialVersionUID = 1L;
	private LocalDateTime dataHora;

	private Integer filme;		
	private Integer critico;		
	
	public  FilterAvaliacao() {
		
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
		
	public Integer getFilme() {
		return filme;
	}
	
	public void setFilme(Integer filme) {
		this.filme = filme;
	}
	public Integer getCritico() {
		return critico;
	}
	
	public void setCritico(Integer critico) {
		this.critico = critico;
	}
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06