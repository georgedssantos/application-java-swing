package br.com.veloe.arquivo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.veloe.arquivo.config.WebViewConfig;

@SpringBootApplication
public class ProcArquivoVeloeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcArquivoVeloeApplication.class, args);
		WebViewConfig.main(null);
	}
	

}
