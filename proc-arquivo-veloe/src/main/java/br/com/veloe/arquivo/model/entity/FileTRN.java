package br.com.veloe.arquivo.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "FILE_TRN")
public class FileTRN {
	
	public FileTRN() {
		this.fileDetails = new ArrayList<>(); 
	}
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipo;
	private String idConcessionaria;
	private String sequencial;
	private String dataGeracao;
	private String horaGeracao;
	private String totalRegistro;
	private String valorTotal;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fileTRN")
	private List<FileDetailTRN>  fileDetails;


}
