package br.com.veloe.arquivo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PassageStatus {
	
    AUTOMATICA(0, "Automática"),
    MANUAL(1, "Manual"),
    EVASAO(2, "Evasão"),
    ISENTO(3, "Isento"),
    LISTA_NELA(4, "Lista Nela");

    private Integer code;
    private String description;
        
    public static PassageStatus getByCodigo(Integer code) {
    	if (code == null)
    		return null;
    	for (PassageStatus e : PassageStatus.values()) {
    		if (e.code.equals(code))
    			return e;
    	}
    	return null;
    }

}
    
