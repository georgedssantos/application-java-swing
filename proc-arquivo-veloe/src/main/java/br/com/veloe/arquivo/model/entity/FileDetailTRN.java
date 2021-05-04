package br.com.veloe.arquivo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.veloe.arquivo.model.enums.PassageStatus;
import br.com.veloe.arquivo.model.enums.TypeCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "FILE_DETAIL_TRN")
public class FileDetailTRN {
	
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_FILE", referencedColumnName = "ID", insertable=false, updatable=false)
	private FileTRN fileTRN;
	
	@Column(name = "ID_FILE")
	public Long idFile;
	
	private String seqRegistro;
	private String emissorTag;
	private String numeroTag;
	private String data;
	private String hora;
	private String praca;
	private String pista;
	private String categoriaTag;
	private String categoriaDetectada;
	@Enumerated(EnumType.STRING)
	private TypeCategory categoriaCobrada;
	private String valorPassagem;
	private String statusCobranca;
	@Enumerated(EnumType.STRING)
	private PassageStatus statusPassagem;
	private String flagBateria;
	private String flagViolacao;
	private String transacao;
	private String placa;

}
