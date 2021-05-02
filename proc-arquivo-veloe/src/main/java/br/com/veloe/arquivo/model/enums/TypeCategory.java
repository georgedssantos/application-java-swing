package br.com.veloe.arquivo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeCategory {
	
    AUTOMOVEL_2E(1, "Automóvel (2 eixos-simples)"),
    CAMINHAO_2D(2, "Caminhão (2 eixos-dupla)"),
    AUTOMOVEL_3E(3, "Automóvel (3 eixos-simples)"),
    CAMINHAO_3D(4, "Caminhão (3 eixos-dupla)"),
    AUTOMOVEL_4E(5, "Automóvel (4 eixos-simples)"),
    CAMINHAO_4D(6, "Caminhão (4 eixos-dupla)"),
    CAMINHAO_5D(7, "Caminhão (5 eixos-dupla)"),
    CAMINHAO_6D(8, "Caminhão (6 eixos-dupla)"),
    MOTOCICLETAS(9, "Motocicletas");
    
   
    private Integer code;
    private String description;

}
