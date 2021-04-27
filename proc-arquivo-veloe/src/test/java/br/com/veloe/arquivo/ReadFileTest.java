package br.com.veloe.arquivo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import br.com.veloe.arquivo.model.entity.FileTRN;

public class ReadFileTest {
	
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		String UPLOAD_DIR = new ClassPathResource("/src/main/resources/uploads/").getPath();
		
		Path path = Paths.get(UPLOAD_DIR + "2021042213465890195.TRN");
		
		List<String> rows = Files.readAllLines(path);
        
        for (String row : rows) {
        	FileTRN file = new FileTRN();
        	
        	String tipo = row.substring(0,2);
            System.out.println("Tipo: "+tipo);
            file.setTipo(tipo);
            
        	String pais = row.substring(2,6);
            System.out.println("Pais: "+pais);
            
            String idConcessionaria = row.substring(6,11);
            System.out.println("idConcessionaria: "+idConcessionaria);
            file.setIdConcessionaria(idConcessionaria);
            
            String sequencial = row.substring(11,16);
            System.out.println("Sequencial: "+sequencial);
            file.setSequencial(sequencial);
            
            String dataGeracao = row.substring(16,24);
            System.out.println("DataGeracao: "+dateFormat(dataGeracao));
            file.setDataGeracao(dateFormat(dataGeracao));
            
            String horaGeracao = row.substring(24,30);
            System.out.println("HoraGeracao: "+timeFormat(horaGeracao));
            file.setHoraGeracao(timeFormat(horaGeracao));
            
            String totalRegistro = row.substring(30,36);
            System.out.println("TotalRegistro: "+totalRegistro);
            file.setTotalRegistro(totalRegistro);
            
            String valorTotal = row.substring(36,48);
            valorTotal = valorTotal.replaceAll("^0*", "");            
            valorTotal = valorTotal.substring(0, valorTotal.length()-2) + "," + valorTotal.substring(valorTotal.length()-2);
            System.out.println("valorTotal: "+valorTotal);
            file.setValorTotal(valorTotal);
            
            break;
         
        }

	}
	
	public static String dateFormat(String date) {
		LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));		
		return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public static String timeFormat(String time) {
		LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmmss"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		return formatter.format(localTime);
	}

}
