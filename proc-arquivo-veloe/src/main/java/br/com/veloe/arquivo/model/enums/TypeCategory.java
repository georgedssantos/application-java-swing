package br.com.veloe.arquivo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeCategory {
	
    AUTOMOVEL_2E("01", "Automóvel (2 eixos-simples)"),
    CAMINHAO_2D("02", "Caminhão (2 eixos-dupla)"),
    AUTOMOVEL_3E("03", "Automóvel (3 eixos-simples)"),
    CAMINHAO_3D("04", "Caminhão (3 eixos-dupla)"),
    AUTOMOVEL_4E("05", "Automóvel (4 eixos-simples)"),
    CAMINHAO_4D("06", "Caminhão (4 eixos-dupla)"),
    CAMINHAO_5D("07", "Caminhão (5 eixos-dupla)"),
    CAMINHAO_6D("08", "Caminhão (6 eixos-dupla)"),
    MOTOCICLETAS("09", "Motocicletas");
    
   
    private String code;
    private String description;
    
    public static TypeCategory getByCodigo(String code) {
    	if (code == null)
    		return null;
    	for (TypeCategory e : TypeCategory.values()) {
    		if (e.code.equals(code))
    			return e;
    	}
    	return null;
    }

}
